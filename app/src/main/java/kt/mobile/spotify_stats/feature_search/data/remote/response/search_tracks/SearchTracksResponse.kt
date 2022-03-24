package kt.mobile.spotify_stats.feature_search.data.remote.response.search_tracks


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.feature_search.domain.models.MySearchItems

data class SearchTracksResponse(
    @SerializedName("tracks")
    val tracks: Tracks
) {
    fun toMySearchItems(): MySearchItems {
        return MySearchItems(
            searchItems = tracks.items.map { it.toMyItem() }
        )
    }
}