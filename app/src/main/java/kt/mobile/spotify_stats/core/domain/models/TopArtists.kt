package kt.mobile.spotify_stats.core.domain.models

import kt.mobile.spotify_stats.core.data.dto.response.get_top_item.ArtistItem
import kt.mobile.spotify_stats.feature_top.domain.models.GenreItem
import kt.mobile.spotify_stats.feature_top.domain.models.TopItem
import kt.mobile.spotify_stats.feature_top.domain.models.TopItems


data class TopArtists(
    val artists: List<ArtistItem> = emptyList()
) {
    fun toArtistsAndGenres(): List<GenreItem> {
        val list = mutableListOf<GenreItem>()

        artists.mapIndexed { index, artist ->
            artist.genres.map { genre ->
                list += GenreItem(genreTitle = genre, genreArtist = artist, artistIndex = index)
            }
        }
        return list
    }

    fun toTopItems(): TopItems {
        return TopItems(
            topItems = artists.map { artist ->
                TopItem(
                    id = artist.id,
                    title = artist.name,
                    imageUrl = artist.images.firstOrNull()?.url
                )
            }
        )
    }

}
