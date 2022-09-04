package kt.mobile.spotify_stats.di

import android.app.Application
import android.content.SharedPreferences
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
import kt.mobile.spotify_stats.core.util.Constants.CROSS_FADE_DURATION
import kt.mobile.spotify_stats.core.util.Constants.IMAGE_TWEEN_DURATION
import kt.mobile.spotify_stats.core.util.Constants.SHARED_PREF_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideImageLoader(app: Application): ImageLoader = ImageLoader.Builder(app)
        .crossfade(true)
        .fallback(R.drawable.ic_placeholder)
        .error(R.drawable.ic_placeholder)
        .crossfade(CROSS_FADE_DURATION)
        .componentRegistry {
            fadeIn(
                animationSpec = tween(
                    durationMillis = IMAGE_TWEEN_DURATION,
                    delayMillis = 0,
                    easing = LinearOutSlowInEasing
                ), initialAlpha = 0f
            )
            add(SvgDecoder(app))
        }.build()

    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(app: Application): SharedPreferences =
        EncryptedSharedPreferences.create(
            app,
            SHARED_PREF_NAME,
            MasterKey.Builder(app)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
}