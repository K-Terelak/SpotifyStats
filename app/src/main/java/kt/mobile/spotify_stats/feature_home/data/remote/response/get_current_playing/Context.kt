package kt.mobile.spotify_stats.feature_home.data.remote.response.get_current_playing


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.core.data.dto.response.ExternalUrls

data class Context(
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    @SerializedName("href")
    val href: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)