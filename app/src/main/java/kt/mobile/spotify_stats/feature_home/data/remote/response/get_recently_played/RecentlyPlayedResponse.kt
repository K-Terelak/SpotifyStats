package kt.mobile.spotify_stats.feature_home.data.remote.response.get_recently_played


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.feature_home.domain.models.RecentlyPlayed

data class RecentlyPlayedResponse(
    @SerializedName("cursors")
    val cursors: Cursors,
    @SerializedName("href")
    val href: String,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("next")
    val next: String
) {
    fun toRecentlyPlayed(): RecentlyPlayed {
        return RecentlyPlayed(
            listSongs = items
        )
    }
}