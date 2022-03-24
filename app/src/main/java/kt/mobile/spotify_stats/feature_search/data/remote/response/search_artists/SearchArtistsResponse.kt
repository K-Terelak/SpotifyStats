package kt.mobile.spotify_stats.feature_search.data.remote.response.search_artists


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.feature_search.domain.models.MySearchItems

data class SearchArtistsResponse(
    @SerializedName("artists")
    val artists: Artists
) {
    fun toMySearchItems(): MySearchItems {
        return MySearchItems(
            searchItems = artists.items.map { it.toSearchItem() }
        )
    }
}