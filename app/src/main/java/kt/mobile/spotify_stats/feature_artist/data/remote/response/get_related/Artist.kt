package kt.mobile.spotify_stats.feature_artist.data.remote.response.get_related


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.core.data.dto.response.ExternalUrls
import kt.mobile.spotify_stats.core.data.dto.response.Followers
import kt.mobile.spotify_stats.core.data.dto.response.Image

data class Artist(
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
)