package kt.mobile.spotify_stats.feature_search.domain.models


data class MySearchItem(
    val id: String,
    val title: String,
    val imageUrl: String? = "",
    val type: String
)
