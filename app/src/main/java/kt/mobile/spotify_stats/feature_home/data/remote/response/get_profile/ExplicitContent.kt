package kt.mobile.spotify_stats.feature_home.data.remote.response.get_profile


import com.google.gson.annotations.SerializedName

data class ExplicitContent(
    @SerializedName("filter_enabled")
    val filterEnabled: Boolean,
    @SerializedName("filter_locked")
    val filterLocked: Boolean
)