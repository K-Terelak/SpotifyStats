package kt.mobile.spotify_stats.feature_track.data.remote

import kt.mobile.spotify_stats.feature_top.data.remote.response.get_tracks_features.TracksFeaturesResponse
import kt.mobile.spotify_stats.feature_track.data.remote.response.get_track.TrackResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrackApi {

    @GET("v1/tracks/{id}")
    suspend fun getTrack(
        @Path("id") id: String
    ): Response<TrackResponse>

    @GET("v1/audio-features")
    suspend fun getTrackFeatures(
        @Query("ids") ids: String
    ): Response<TracksFeaturesResponse>

}