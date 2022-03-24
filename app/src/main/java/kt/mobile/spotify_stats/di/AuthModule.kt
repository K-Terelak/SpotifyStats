package kt.mobile.spotify_stats.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kt.mobile.spotify_stats.feature_auth.data.remote.AuthApi
import kt.mobile.spotify_stats.feature_auth.data.repository.AuthRepositoryImpl
import kt.mobile.spotify_stats.feature_auth.domain.repository.AuthRepository
import kt.mobile.spotify_stats.feature_auth.domain.use_case.AuthUseCases
import kt.mobile.spotify_stats.feature_auth.domain.use_case.GetAuthTokenUseCase
import kt.mobile.spotify_stats.feature_auth.domain.use_case.GetRefreshTokenUseCase
import kt.mobile.spotify_stats.feature_auth.domain.use_case.LogoutUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(
        @Named("bez") client: OkHttpClient,
    ): AuthApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(AuthApi.BASE_URL)
            .client(client)
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: AuthApi,
        sharedPreferences: SharedPreferences,
    ): AuthRepository {
        return AuthRepositoryImpl(api, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideAuthUseCases(repository: AuthRepository): AuthUseCases {
        return AuthUseCases(
            getAuthToken = GetAuthTokenUseCase(repository),
            getRefreshToken = GetRefreshTokenUseCase(repository),
            logout = LogoutUseCase(repository)
        )
    }
}