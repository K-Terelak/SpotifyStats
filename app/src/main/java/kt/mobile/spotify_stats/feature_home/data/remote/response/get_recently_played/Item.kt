package kt.mobile.spotify_stats.feature_home.data.remote.response.get_recently_played


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.core.data.dto.response.Track

data class Item(
    @SerializedName("context")
    val context: Any,
    @SerializedName("played_at")
    val playedAt: String,
    @SerializedName("track")
    val track: Track
)