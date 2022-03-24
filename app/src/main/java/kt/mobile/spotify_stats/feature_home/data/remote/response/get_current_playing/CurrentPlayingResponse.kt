package kt.mobile.spotify_stats.feature_home.data.remote.response.get_current_playing


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.core.data.dto.response.Track
import kt.mobile.spotify_stats.feature_home.domain.models.CurrentlyPlaying

data class CurrentPlayingResponse(
    @SerializedName("actions")
    val actions: Actions,
    @SerializedName("context")
    val context: Context,
    @SerializedName("currently_playing_type")
    val currentlyPlayingType: String,
    @SerializedName("is_playing")
    val isPlaying: Boolean,
    @SerializedName("item")
    val track: Track?,
    @SerializedName("progress_ms")
    val progressMs: Int,
    @SerializedName("timestamp")
    val timestamp: Long
) {

    fun toCurrentlyPlaying(): CurrentlyPlaying {
        return CurrentlyPlaying(
            track = track
        )
    }
}