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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackModule {

    @Provides
    @Singleton
    fun provideTrackApi(
        @Named("token") client: OkHttpClient,
    ): TrackApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(TrackApi.BASE_URL)
            .client(client)
            .build()
            .create(TrackApi::class.java)
    }

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