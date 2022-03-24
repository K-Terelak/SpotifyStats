package kt.mobile.spotify_stats.feature_track.data.remote.response.get_track


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.core.data.dto.response.Album
import kt.mobile.spotify_stats.core.data.dto.response.Artist
import kt.mobile.spotify_stats.core.data.dto.response.ExternalIds
import kt.mobile.spotify_stats.core.data.dto.response.ExternalUrls
import kt.mobile.spotify_stats.feature_track.domain.models.MyTrack

data class TrackResponse(
    @SerializedName("album")
    val album: Album,
    @SerializedName("artists")
    val artists: List<Artist>,
    @SerializedName("available_markets")
    val availableMarkets: List<String>,
    @SerializedName("disc_number")
    val discNumber: Int,
    @SerializedName("duration_ms")
    val durationMs: Int,
    @SerializedName("explicit")
    val explicit: Boolean,
    @SerializedName("external_ids")
    val externalIds: ExternalIds,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_local")
    val isLocal: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("preview_url")
    val previewUrl: String,
    @SerializedName("track_number")
    val trackNumber: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
) {
    fun toTrack(): MyTrack {
        return MyTrack(
            id = id,
            name = name,
            popularity = popularity,
            album = album,
            artists = artists,
            durationMs = durationMs
        )
    }
}