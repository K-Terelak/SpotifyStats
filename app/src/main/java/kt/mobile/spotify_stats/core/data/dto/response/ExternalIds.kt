package kt.mobile.spotify_stats.core.data.dto.response


import com.google.gson.annotations.SerializedName

data class ExternalIds(
    @SerializedName("isrc")
    val isrc: String
)