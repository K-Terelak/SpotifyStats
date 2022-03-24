package kt.mobile.spotify_stats.feature_top.data.remote.response.get_top_tracks


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.core.data.dto.response.Track
import kt.mobile.spotify_stats.feature_top.domain.models.TopTracks

data class TopTracksResponse(
    @SerializedName("href")
    val href: String,
    @SerializedName("items")
    val trackItems: List<Track>,
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
) {
    fun toTopTracks(): TopTracks {
        return TopTracks(
            tracks = trackItems
        )
    }

}