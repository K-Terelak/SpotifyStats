package kt.mobile.spotify_stats.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kt.mobile.spotify_stats.feature_home.data.remote.HomeApi
import kt.mobile.spotify_stats.feature_home.data.repository.HomeRepositoryImpl
import kt.mobile.spotify_stats.feature_home.domain.repository.HomeRepository
import kt.mobile.spotify_stats.feature_home.domain.use_case.GetCurrentlyPlayingUseCase
import kt.mobile.spotify_stats.feature_home.domain.use_case.GetMyProfileUseCase
import kt.mobile.spotify_stats.feature_home.domain.use_case.GetRecentlyPlayedUseCase
import kt.mobile.spotify_stats.feature_home.domain.use_case.HomeUseCases
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {


    @Singleton
    @Provides
    fun provideHomeApi(
        @Named("retrofitToken") retrofit: Retrofit
    ): HomeApi = retrofit.create(HomeApi::class.java)


    @Provides
    @Singleton
    fun provideHomeRepository(
        api: HomeApi,
        sharedPreferences: SharedPreferences
    ): HomeRepository {
        return HomeRepositoryImpl(api, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideHomeUseCases(repository: HomeRepository): HomeUseCases {
        return HomeUseCases(
            getMyProfile = GetMyProfileUseCase(repository),
            getCurrentlyPlaying = GetCurrentlyPlayingUseCase(repository),
            getRecentlyPlayed = GetRecentlyPlayedUseCase(repository)
        )
    }

}