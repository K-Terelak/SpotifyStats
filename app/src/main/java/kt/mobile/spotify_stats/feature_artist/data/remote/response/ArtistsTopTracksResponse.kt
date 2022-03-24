package kt.mobile.spotify_stats.feature_artist.data.remote.response


import com.google.gson.annotations.SerializedName

data class ArtistsTopTracksResponse(
    @SerializedName("tracks")
    val tracks: List<Track>
)
