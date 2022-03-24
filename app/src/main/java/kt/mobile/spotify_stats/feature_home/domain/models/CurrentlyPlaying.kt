package kt.mobile.spotify_stats.feature_home.domain.models

import kt.mobile.spotify_stats.core.data.dto.response.Track

data class CurrentlyPlaying(
    val track: Track? = null
)