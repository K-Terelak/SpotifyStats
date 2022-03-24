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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TopModule {

    @Provides
    @Singleton
    fun provideTopApi(
        @Named("token") client: OkHttpClient,
    ): TopApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(TopApi.BASE_URL)
            .client(client)
            .build()
            .create(TopApi::class.java)
    }

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