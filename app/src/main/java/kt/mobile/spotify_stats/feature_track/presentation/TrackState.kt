package kt.mobile.spotify_stats.feature_track.presentation

import kt.mobile.spotify_stats.feature_top.domain.models.TracksFeatures
import kt.mobile.spotify_stats.feature_track.domain.models.MyTrack

data class TrackState(
    val track: MyTrack? = null,
    val trackFeatures: TracksFeatures? = null,
    val trackError: String = "",
    val isTrackLoading: Boolean = false,
    val isTrackFeaturesLoading: Boolean = false,
    val trackFeaturesError: String = ""
)
