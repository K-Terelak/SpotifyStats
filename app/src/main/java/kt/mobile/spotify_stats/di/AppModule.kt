package kt.mobile.spotify_stats.di

import android.app.Application
import android.content.SharedPreferences
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import coil.ImageLoader
import coil.decode.SvgDecoder
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.util.Constants
import kt.mobile.spotify_stats.core.util.Constants.SHARED_PREF_NAME
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("bez")
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        app: Application
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                val ai: ApplicationInfo = app.packageManager
                    .getApplicationInfo(app.packageName, PackageManager.GET_META_DATA)
                val clientId = ai.metaData["client_id"].toString()
                val clientSecret = ai.metaData["client_secret"].toString()
                val encoded =
                    Base64.getEncoder().encodeToString(("$clientId:$clientSecret").toByteArray())

                val modifiedRequest = it.request().newBuilder()
                    .addHeader("Authorization", "Basic $encoded")
                    .build()
                it.proceed(modifiedRequest)
            }
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Named("token")
    @Provides
    @Singleton
    fun provideOkHttpClientToken(
        sharedPreferences: SharedPreferences,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                val token = sharedPreferences.getString(Constants.KEY_BEARER_TOKEN, "")
                val modifiedRequest = it.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                it.proceed(modifiedRequest)
            }
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideImageLoader(app: Application): ImageLoader {
        return ImageLoader.Builder(app)
            .crossfade(true)
            .fallback(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .crossfade(1000)
            .componentRegistry {
                fadeIn(
                    animationSpec = tween(
                        durationMillis = 200,
                        delayMillis = 0,
                        easing = LinearOutSlowInEasing
                    ), 0f
                )
                add(SvgDecoder(app))
            }.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(app: Application): SharedPreferences {
        return EncryptedSharedPreferences.create(
            app,
            SHARED_PREF_NAME,
            MasterKey.Builder(app)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


}