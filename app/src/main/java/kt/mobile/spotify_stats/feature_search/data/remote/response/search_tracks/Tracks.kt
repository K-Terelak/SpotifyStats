package kt.mobile.spotify_stats.feature_search.data.remote.response.search_tracks


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.core.data.dto.response.Track

data class Tracks(
    @SerializedName("href")
    val href: String,
    @SerializedName("items")
    val items: List<Track>,
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