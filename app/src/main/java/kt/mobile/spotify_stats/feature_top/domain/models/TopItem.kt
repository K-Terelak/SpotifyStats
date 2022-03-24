package kt.mobile.spotify_stats.feature_top.domain.models

data class TopItem(
    val id: String,
    val title: String,
    val imageUrl: String? = "",
    val genres: GenreItem? = null
)
