package kt.mobile.spotify_stats.feature_home.domain.models

import kt.mobile.spotify_stats.feature_home.data.remote.response.get_recently_played.Item

data class RecentlyPlayed(
    val listSongs: List<Item>
)
