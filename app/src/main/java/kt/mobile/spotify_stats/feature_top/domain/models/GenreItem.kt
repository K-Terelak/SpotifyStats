package kt.mobile.spotify_stats.feature_top.domain.models

import kt.mobile.spotify_stats.core.data.dto.response.get_top_item.ArtistItem

data class GenreItem(
    val genreTitle: String,
    val genreArtist: ArtistItem,
    val artistIndex: Int
)
