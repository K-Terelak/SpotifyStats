package kt.mobile.spotify_stats.feature_global.data.remote.response.get_top_global


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.core.data.dto.response.ExternalUrls

data class AddedBy(
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)