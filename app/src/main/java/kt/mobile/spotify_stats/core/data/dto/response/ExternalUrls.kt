package kt.mobile.spotify_stats.core.data.dto.response


import com.google.gson.annotations.SerializedName

data class ExternalUrls(
    @SerializedName("spotify")
    val spotify: String
)