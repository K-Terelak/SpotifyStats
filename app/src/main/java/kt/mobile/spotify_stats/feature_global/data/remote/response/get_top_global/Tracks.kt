package kt.mobile.spotify_stats.feature_global.data.remote.response.get_top_global


import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("href")
    val href: String,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("total")
    val total: Int
)