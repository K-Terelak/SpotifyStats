package kt.mobile.spotify_stats.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kt.mobile.spotify_stats.feature_artist.domain.repository.ArtistRepository
import kt.mobile.spotify_stats.feature_artist.domain.use_case.ArtistUseCases
import kt.mobile.spotify_stats.feature_artist.domain.use_case.GetArtistUseCase
import kt.mobile.spotify_stats.feature_artist.domain.use_case.GetArtistsTopTracksUseCase
import kt.mobile.spotify_stats.feature_artist.domain.use_case.GetRelatedArtistsUseCase
import kt.mobile.spotify_stats.feature_auth.domain.repository.AuthRepository
import kt.mobile.spotify_stats.feature_auth.domain.use_case.AuthUseCases
import kt.mobile.spotify_stats.feature_auth.domain.use_case.GetAuthTokenUseCase
import kt.mobile.spotify_stats.feature_auth.domain.use_case.GetRefreshTokenUseCase
import kt.mobile.spotify_stats.feature_auth.domain.use_case.LogoutUseCase
import kt.mobile.spotify_stats.feature_global.domain.repository.GlobalRepository
import kt.mobile.spotify_stats.feature_global.domain.use_case.GetTopGlobalUseCase
import kt.mobile.spotify_stats.feature_global.domain.use_case.GlobalUseCases
import kt.mobile.spotify_stats.feature_home.domain.repository.HomeRepository
import kt.mobile.spotify_stats.feature_home.domain.use_case.GetCurrentlyPlayingUseCase
import kt.mobile.spotify_stats.feature_home.domain.use_case.GetMyProfileUseCase
import kt.mobile.spotify_stats.feature_home.domain.use_case.GetRecentlyPlayedUseCase
import kt.mobile.spotify_stats.feature_home.domain.use_case.HomeUseCases
import kt.mobile.spotify_stats.feature_search.domain.repository.SearchRepository
import kt.mobile.spotify_stats.feature_search.domain.use_case.GetSearchArtistsUseCase
import kt.mobile.spotify_stats.feature_search.domain.use_case.GetSearchTracksUseCase
import kt.mobile.spotify_stats.feature_search.domain.use_case.SearchUseCases
import kt.mobile.spotify_stats.feature_top.domain.repository.TopRepository
import kt.mobile.spotify_stats.feature_top.domain.use_case.GetTopArtistsUseCase
import kt.mobile.spotify_stats.feature_top.domain.use_case.GetTopItemsUseCase
import kt.mobile.spotify_stats.feature_top.domain.use_case.GetTracksFeaturesUseCase
import kt.mobile.spotify_stats.feature_top.domain.use_case.TopUseCases
import kt.mobile.spotify_stats.feature_track.domain.repository.TrackRepository
import kt.mobile.spotify_stats.feature_track.domain.use_case.GetTrackFeaturesUseCase
import kt.mobile.spotify_stats.feature_track.domain.use_case.GetTrackUseCase
import kt.mobile.spotify_stats.feature_track.domain.use_case.TrackUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideArtistUseCases(repository: ArtistRepository): ArtistUseCases = ArtistUseCases(
        getArtist = GetArtistUseCase(repository),
        getArtistsTopTracks = GetArtistsTopTracksUseCase(repository),
        getRelatedArtists = GetRelatedArtistsUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideAuthUseCases(repository: AuthRepository): AuthUseCases = AuthUseCases(
        getAuthToken = GetAuthTokenUseCase(repository),
        getRefreshToken = GetRefreshTokenUseCase(repository),
        logout = LogoutUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideGlobalUseCases(repository: GlobalRepository): GlobalUseCases = GlobalUseCases(
        getTopGlobal = GetTopGlobalUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideHomeUseCases(repository: HomeRepository): HomeUseCases = HomeUseCases(
        getMyProfile = GetMyProfileUseCase(repository),
        getCurrentlyPlaying = GetCurrentlyPlayingUseCase(repository),
        getRecentlyPlayed = GetRecentlyPlayedUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideSearchUseCases(repository: SearchRepository): SearchUseCases = SearchUseCases(
        searchArtists = GetSearchArtistsUseCase(repository),
        searchTracks = GetSearchTracksUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideTopUseCases(repository: TopRepository): TopUseCases = TopUseCases(
        getTopArtists = GetTopArtistsUseCase(repository),
        getTopItems = GetTopItemsUseCase(repository),
        getTracksFeatures = GetTracksFeaturesUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideTrackUseCases(repository: TrackRepository): TrackUseCases = TrackUseCases(
        getTrack = GetTrackUseCase(repository),
        getTrackFeatures = GetTrackFeaturesUseCase(repository)
    )
}