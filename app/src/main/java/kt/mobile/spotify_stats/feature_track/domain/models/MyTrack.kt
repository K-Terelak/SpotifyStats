package kt.mobile.spotify_stats.feature_track.domain.models

import kt.mobile.spotify_stats.core.data.dto.response.Album
import kt.mobile.spotify_stats.core.data.dto.response.Artist

data class MyTrack(
    val id: String,
    val name: String,
    val popularity: Int,
    val album: Album,
    val artists: List<Artist>,
    val durationMs: Int,
)
