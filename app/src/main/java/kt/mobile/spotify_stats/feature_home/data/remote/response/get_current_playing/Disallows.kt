package kt.mobile.spotify_stats.feature_home.data.remote.response.get_current_playing


import com.google.gson.annotations.SerializedName

data class Disallows(
    @SerializedName("resuming")
    val resuming: Boolean
)