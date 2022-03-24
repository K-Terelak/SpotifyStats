package kt.mobile.spotify_stats.feature_artist.data.remote.response.get_related


import com.google.gson.annotations.SerializedName

data class RelatedArtistsResponse(
    @SerializedName("artists")
    val artists: List<Artist>
)