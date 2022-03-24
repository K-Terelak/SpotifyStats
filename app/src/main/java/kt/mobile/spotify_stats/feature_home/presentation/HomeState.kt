package kt.mobile.spotify_stats.feature_home.presentation

import kt.mobile.spotify_stats.core.domain.models.TopArtists
import kt.mobile.spotify_stats.feature_home.domain.models.CurrentlyPlaying
import kt.mobile.spotify_stats.feature_home.domain.models.MyProfile
import kt.mobile.spotify_stats.feature_home.domain.models.RecentlyPlayed

data class HomeState(

    val isMyProfileLoading: Boolean = false,
    val myProfile: MyProfile? = null,
    val profileError: String = "",

    val isCurrentlyPlayingLoading: Boolean = false,
    val currentlyPlaying: CurrentlyPlaying? = null,
    val currentlyPlayingError: String = "",

    val isTopArtistsLoading: Boolean = false,
    val topArtists: TopArtists? = null,
    val topArtistsError: String = "",

    val isRecentlyPlayedLoading: Boolean = false,
    val recentlyPlayed: RecentlyPlayed? = null,
    val recentlyPlayedError: String = "",
)
