package kt.mobile.spotify_stats.feature_top.presentation

import kt.mobile.spotify_stats.feature_top.domain.models.TopTracks
import kt.mobile.spotify_stats.feature_top.domain.models.TracksFeatures

data class TopTracksState(
    val isTracksFeaturesLoading: Boolean = false,
    val isTopTracksLoading: Boolean = false,
    val topTracksFeatures: TracksFeatures? = null,
    val topTracksList: TopTracks? = null,
    val topTracksError: Int? = null,
    val topTracksFeaturesError: Int? = null,
)
