package kt.mobile.spotify_stats.feature_global.presentation

import kt.mobile.spotify_stats.feature_global.data.remote.response.get_top_global.Item

data class GlobalState(
    val tracks: List<Item>? = emptyList(),
    val isTracksLoading: Boolean = false,
    val isTracksError: String = ""
)
