package kt.mobile.spotify_stats.feature_home.data.remote.response.get_recently_played


import com.google.gson.annotations.SerializedName

data class Cursors(
    @SerializedName("after")
    val after: String,
    @SerializedName("before")
    val before: String
)