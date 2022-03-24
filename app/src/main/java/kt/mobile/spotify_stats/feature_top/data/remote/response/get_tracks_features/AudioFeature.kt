package kt.mobile.spotify_stats.feature_top.data.remote.response.get_tracks_features


import com.google.gson.annotations.SerializedName

data class AudioFeature(
    @SerializedName("acousticness")
    val acousticness: Double,
    @SerializedName("analysis_url")
    val analysisUrl: String,
    @SerializedName("danceability")
    val danceability: Double,
    @SerializedName("duration_ms")
    val durationMs: Int,
    @SerializedName("energy")
    val energy: Double,
    @SerializedName("id")
    val id: String,
    @SerializedName("instrumentalness")
    val instrumentalness: Double,
    @SerializedName("key")
    val key: Int,
    @SerializedName("liveness")
    val liveness: Double,
    @SerializedName("loudness")
    val loudness: Double,
    @SerializedName("mode")
    val mode: Int,
    @SerializedName("speechiness")
    val speechiness: Double,
    @SerializedName("tempo")
    val tempo: Double,
    @SerializedName("time_signature")
    val timeSignature: Int,
    @SerializedName("track_href")
    val trackHref: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String,
    @SerializedName("valence")
    val valence: Double
)