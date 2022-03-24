package kt.mobile.spotify_stats.feature_global.data.remote.response.get_top_global


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.core.data.dto.response.ExternalUrls
import kt.mobile.spotify_stats.core.data.dto.response.Followers
import kt.mobile.spotify_stats.core.data.dto.response.Image

data class TopGlobalResponse(
    @SerializedName("collaborative")
    val collaborative: Boolean,
    @SerializedName("description")
    val description: String,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    @SerializedName("followers")
    val followers: Followers,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("primary_color")
    val primaryColor: Any,
    @SerializedName("public")
    val `public`: Boolean,
    @SerializedName("snapshot_id")
    val snapshotId: String,
    @SerializedName("tracks")
    val tracks: Tracks,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)