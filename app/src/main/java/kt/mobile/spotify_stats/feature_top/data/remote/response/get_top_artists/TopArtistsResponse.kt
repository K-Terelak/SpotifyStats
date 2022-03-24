package kt.mobile.spotify_stats.feature_top.data.remote.response.get_top_artists


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.core.data.dto.response.get_top_item.ArtistItem
import kt.mobile.spotify_stats.core.domain.models.TopArtists

data class TopArtistsResponse(
    @SerializedName("href")
    val href: String,
    @SerializedName("items")
    val artistItems: List<ArtistItem>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("total")
    val total: Int
) {
    fun toTopArtists(): TopArtists {
        return TopArtists(
            artists = artistItems,
        )
    }

}