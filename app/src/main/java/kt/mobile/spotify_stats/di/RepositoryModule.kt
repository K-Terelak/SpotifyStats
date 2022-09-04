package kt.mobile.spotify_stats.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kt.mobile.spotify_stats.feature_artist.data.remote.ArtistApi
import kt.mobile.spotify_stats.feature_artist.data.repository.ArtistRepositoryImpl
import kt.mobile.spotify_stats.feature_artist.domain.repository.ArtistRepository
import kt.mobile.spotify_stats.feature_auth.data.remote.AuthApi
import kt.mobile.spotify_stats.feature_auth.data.repository.AuthRepositoryImpl
import kt.mobile.spotify_stats.feature_auth.domain.repository.AuthRepository
import kt.mobile.spotify_stats.feature_global.data.remote.GlobalApi
import kt.mobile.spotify_stats.feature_global.data.repository.GlobalRepositoryImpl
import kt.mobile.spotify_stats.feature_global.domain.repository.GlobalRepository
import kt.mobile.spotify_stats.feature_home.data.remote.HomeApi
import kt.mobile.spotify_stats.feature_home.data.repository.HomeRepositoryImpl
import kt.mobile.spotify_stats.feature_home.domain.repository.HomeRepository
import kt.mobile.spotify_stats.feature_search.data.remote.SearchApi
import kt.mobile.spotify_stats.feature_search.data.repository.SearchRepositoryImpl
import kt.mobile.spotify_stats.feature_search.domain.repository.SearchRepository
import kt.mobile.spotify_stats.feature_top.data.remote.TopApi
import kt.mobile.spotify_stats.feature_top.data.repository.TopRepositoryImpl
import kt.mobile.spotify_stats.feature_top.domain.repository.TopRepository
import kt.mobile.spotify_stats.feature_track.data.remote.TrackApi
import kt.mobile.spotify_stats.feature_track.data.repository.TrackRepositoryImpl
import kt.mobile.spotify_stats.feature_track.domain.repository.TrackRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideArtistRepository(
        api: ArtistApi,
        sharedPreferences: SharedPreferences
    ): ArtistRepository = ArtistRepositoryImpl(api, sharedPreferences)

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: AuthApi,
        sharedPreferences: SharedPreferences,
    ): AuthRepository = AuthRepositoryImpl(api, sharedPreferences)

    @Provides
    @Singleton
    fun provideGlobalRepository(
        api: GlobalApi,
    ): GlobalRepository = GlobalRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideHomeRepository(
        api: HomeApi,
        sharedPreferences: SharedPreferences
    ): HomeRepository = HomeRepositoryImpl(api, sharedPreferences)

    @Provides
    @Singleton
    fun provideSearchRepository(
        api: SearchApi,
    ): SearchRepository = SearchRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideTopRepository(
        api: TopApi,
    ): TopRepository = TopRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideTrackRepository(
        api: TrackApi,
    ): TrackRepository = TrackRepositoryImpl(api)
}