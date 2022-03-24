package kt.mobile.spotify_stats.feature_search.data.remote.response.search_artists


import com.google.gson.annotations.SerializedName

data class Artists(
    @SerializedName("href")
    val href: String,
    @SerializedName("items")
    val items: List<Item>,
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
)