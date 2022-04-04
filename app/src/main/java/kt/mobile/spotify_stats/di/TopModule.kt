package kt.mobile.spotify_stats.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kt.mobile.spotify_stats.feature_top.data.remote.TopApi
import kt.mobile.spotify_stats.feature_top.data.repository.TopRepositoryImpl
import kt.mobile.spotify_stats.feature_top.domain.repository.TopRepository
import kt.mobile.spotify_stats.feature_top.domain.use_case.GetTopArtistsUseCase
import kt.mobile.spotify_stats.feature_top.domain.use_case.GetTopItemsUseCase
import kt.mobile.spotify_stats.feature_top.domain.use_case.GetTracksFeaturesUseCase
import kt.mobile.spotify_stats.feature_top.domain.use_case.TopUseCases
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TopModule {


    @Singleton
    @Provides
    fun provideTopApi(@Named("retrofitToken")retrofit: Retrofit): TopApi = retrofit.create(TopApi::class.java)


    @Provides
    @Singleton
    fun provideTopRepository(
        api: TopApi,
    ): TopRepository {
        return TopRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideHomeUseCases(repository: TopRepository): TopUseCases {
        return TopUseCases(
            getTopArtists = GetTopArtistsUseCase(repository),
            getTopItems = GetTopItemsUseCase(repository),
            getTracksFeatures = GetTracksFeaturesUseCase(repository)
        )
    }
}