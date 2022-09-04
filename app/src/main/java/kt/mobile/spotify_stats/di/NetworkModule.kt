package kt.mobile.spotify_stats.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kt.mobile.spotify_stats.BuildConfig
import kt.mobile.spotify_stats.core.util.Constants
import kt.mobile.spotify_stats.core.util.encodeBasicHeader
import kt.mobile.spotify_stats.core.util.toBearerHeader
import kt.mobile.spotify_stats.feature_artist.data.remote.ArtistApi
import kt.mobile.spotify_stats.feature_auth.data.remote.AuthApi
import kt.mobile.spotify_stats.feature_global.data.remote.GlobalApi
import kt.mobile.spotify_stats.feature_home.data.remote.HomeApi
import kt.mobile.spotify_stats.feature_search.data.remote.SearchApi
import kt.mobile.spotify_stats.feature_top.data.remote.TopApi
import kt.mobile.spotify_stats.feature_track.data.remote.TrackApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @ClientAuth
    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { interceptorChain ->
            val modifiedRequest = interceptorChain.request().newBuilder()
                .addHeader(Constants.AUTHORIZATION_HEADER, encodeBasicHeader())
                .build()
            interceptorChain.proceed(modifiedRequest)
        }
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @ClientBearer
    @Provides
    @Singleton
    fun provideOkHttpClientToken(
        sharedPreferences: SharedPreferences,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val token = sharedPreferences.getString(Constants.KEY_BEARER_TOKEN, "") ?: ""
            val modifiedRequest = it.request().newBuilder()
                .addHeader(Constants.AUTHORIZATION_HEADER, token.toBearerHeader())
                .build()
            it.proceed(modifiedRequest)
        }
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @RetrofitToken
    @Provides
    @Singleton
    fun provideRetrofitToken(@ClientBearer okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_API_URL)
            .client(okHttpClient)
            .build()

    @RetrofitAuth
    @Provides
    @Singleton
    fun provideRetrofitAuth(@ClientAuth okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_AUTH_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

    @Provides
    @Singleton
    fun provideArtistApi(@RetrofitToken retrofit: Retrofit): ArtistApi =
        retrofit.create(ArtistApi::class.java)

    @Provides
    @Singleton
    fun provideAuthApi(
        @RetrofitAuth retrofit: Retrofit
    ): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideGlobalApi(
        @RetrofitToken retrofit: Retrofit
    ): GlobalApi = retrofit.create(GlobalApi::class.java)

    @Provides
    @Singleton
    fun provideHomeApi(
        @RetrofitToken retrofit: Retrofit
    ): HomeApi = retrofit.create(HomeApi::class.java)

    @Provides
    @Singleton
    fun provideSearchApi(@RetrofitToken retrofit: Retrofit): SearchApi =
        retrofit.create(SearchApi::class.java)

    @Provides
    @Singleton
    fun provideTopApi(@RetrofitToken retrofit: Retrofit): TopApi =
        retrofit.create(TopApi::class.java)

    @Provides
    @Singleton
    fun provideTrackApi(@RetrofitToken retrofit: Retrofit): TrackApi =
        retrofit.create(TrackApi::class.java)
}