package kt.mobile.spotify_stats.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kt.mobile.spotify_stats.feature_search.data.remote.SearchApi
import kt.mobile.spotify_stats.feature_search.data.repository.SearchRepositoryImpl
import kt.mobile.spotify_stats.feature_search.domain.repository.SearchRepository
import kt.mobile.spotify_stats.feature_search.domain.use_case.GetSearchArtistsUseCase
import kt.mobile.spotify_stats.feature_search.domain.use_case.GetSearchTracksUseCase
import kt.mobile.spotify_stats.feature_search.domain.use_case.SearchUseCases
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {


    @Singleton
    @Provides
    fun provideSearchApi(@Named("retrofitToken")retrofit: Retrofit): SearchApi = retrofit.create(SearchApi::class.java)


    @Provides
    @Singleton
    fun provideSearchRepository(
        api: SearchApi,
    ): SearchRepository {
        return SearchRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideSearchUseCases(repository: SearchRepository): SearchUseCases {
        return SearchUseCases(
            searchArtists = GetSearchArtistsUseCase(repository),
            searchTracks = GetSearchTracksUseCase(repository)
        )
    }
}