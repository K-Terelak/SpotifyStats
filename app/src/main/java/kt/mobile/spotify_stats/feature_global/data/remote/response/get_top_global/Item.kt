package kt.mobile.spotify_stats.feature_global.data.remote.response.get_top_global


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.core.data.dto.response.Track

data class Item(
    @SerializedName("added_at")
    val addedAt: String,
    @SerializedName("added_by")
    val addedBy: AddedBy,
    @SerializedName("is_local")
    val isLocal: Boolean,
    @SerializedName("primary_color")
    val primaryColor: Any,
    @SerializedName("track")
    val track: Track,
    @SerializedName("video_thumbnail")
    val videoThumbnail: VideoThumbnail
)