package kt.mobile.spotify_stats.feature_search.data.remote.response.search_artists


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.core.data.dto.response.ExternalUrls
import kt.mobile.spotify_stats.core.data.dto.response.Followers
import kt.mobile.spotify_stats.core.data.dto.response.Image
import kt.mobile.spotify_stats.feature_search.domain.models.MySearchItem

data class Item(
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    @SerializedName("followers")
    val followers: Followers,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("name")
    val name: String,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
) {
    fun toSearchItem(): MySearchItem {
        return MySearchItem(
            id = id,
            title = name,
            imageUrl = images.firstOrNull()?.url,
            type = type
        )
    }
}
