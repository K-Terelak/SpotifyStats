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
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GlobalModule {


    @Singleton
    @Provides
    fun provideGlobalApi(
        @Named("retrofitToken") retrofit: Retrofit
    ): GlobalApi = retrofit.create(GlobalApi::class.java)


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