package kt.mobile.spotify_stats.feature_top.data.remote.response.get_tracks_features


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.feature_top.domain.models.FeatureItem
import kt.mobile.spotify_stats.feature_top.domain.models.TracksFeatures

data class TracksFeaturesResponse(
    @SerializedName("audio_features")
    val audioFeatures: List<AudioFeature>
) {

    fun toTracksFeatures(): TracksFeatures {

        val list = mutableListOf<FeatureItem>()

        list += FeatureItem(
            title = "acoustic",
            value = (audioFeatures.map { it.acousticness }.average()).toFloat()
        )
        list += FeatureItem(
            title = "danceable",
            value = (audioFeatures.map { it.danceability }.average()).toFloat()
        )
        list += FeatureItem(
            title = "energetic",
            value = (audioFeatures.map { it.energy }.average()).toFloat()
        )
        list += FeatureItem(
            title = "instrumental",
            value = (audioFeatures.map { it.instrumentalness }.average()).toFloat()
        )
        list += FeatureItem(
            title = "lively",
            value = (audioFeatures.map { it.liveness }.average()).toFloat()
        )
        list += FeatureItem(
            title = "speechful",
            value = (audioFeatures.map { it.speechiness }.average()).toFloat()
        )

        return TracksFeatures(listFeatures = list)
    }
}