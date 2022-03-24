package kt.mobile.spotify_stats.core.data.dto.response

import com.google.gson.annotations.SerializedName

data class Followers(
    @SerializedName("href")
    val href: Any,
    @SerializedName("total")
    val total: Int
)