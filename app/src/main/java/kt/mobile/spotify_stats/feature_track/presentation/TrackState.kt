package kt.mobile.spotify_stats.feature_track.presentation

import kt.mobile.spotify_stats.feature_top.domain.models.TracksFeatures
import kt.mobile.spotify_stats.feature_track.domain.models.MyTrack

data class TrackState(
    val track: MyTrack? = null,
    val trackFeatures: TracksFeatures? = null,
    val trackError: Int? = null,
    val isTrackLoading: Boolean = false,
    val isTrackFeaturesLoading: Boolean = false,
    val trackFeaturesError: Int? = null
)
