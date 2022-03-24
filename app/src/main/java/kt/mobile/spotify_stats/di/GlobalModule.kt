package kt.mobile.spotify_stats.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kt.mobile.spotify_stats.feature_global.data.remote.GlobalApi
import kt.mobile.spotify_stats.feature_global.data.repository.GlobalRepositoryImpl
import kt.mobile.spotify_stats.feature_global.domain.repository.GlobalRepository
import kt.mobile.spotify_stats.feature_global.domain.use_case.GetTopGlobalUseCase
import kt.mobile.spotify_stats.feature_global.domain.use_case.GlobalUseCases
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GlobalModule {

    @Provides
    @Singleton
    fun provideGlobalApi(
        @Named("token") client: OkHttpClient,
    ): GlobalApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GlobalApi.BASE_URL)
            .client(client)
            .build()
            .create(GlobalApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGlobalRepository(
        api: GlobalApi,
    ): GlobalRepository {
        return GlobalRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGlobalUseCases(repository: GlobalRepository): GlobalUseCases {
        return GlobalUseCases(
            getTopGlobal = GetTopGlobalUseCase(repository)
        )
    }
}