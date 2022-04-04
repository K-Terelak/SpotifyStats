package kt.mobile.spotify_stats.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kt.mobile.spotify_stats.feature_track.data.remote.TrackApi
import kt.mobile.spotify_stats.feature_track.data.repository.TrackRepositoryImpl
import kt.mobile.spotify_stats.feature_track.domain.repository.TrackRepository
import kt.mobile.spotify_stats.feature_track.domain.use_case.GetTrackFeaturesUseCase
import kt.mobile.spotify_stats.feature_track.domain.use_case.GetTrackUseCase
import kt.mobile.spotify_stats.feature_track.domain.use_case.TrackUseCases
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackModule {


    @Singleton
    @Provides
    fun provideTrackApi(@Named("retrofitToken") retrofit: Retrofit): TrackApi =
        retrofit.create(TrackApi::class.java)


    @Provides
    @Singleton
    fun provideTrackRepository(
        api: TrackApi,
    ): TrackRepository {
        return TrackRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideTrackUseCases(repository: TrackRepository): TrackUseCases {
        return TrackUseCases(
            getTrack = GetTrackUseCase(repository),
            getTrackFeatures = GetTrackFeaturesUseCase(repository)
        )
    }
}