package kt.mobile.spotify_stats.feature_top.data.remote

import kt.mobile.spotify_stats.feature_top.data.remote.response.get_top_artists.TopArtistsResponse
import kt.mobile.spotify_stats.feature_top.data.remote.response.get_top_tracks.TopTracksResponse
import kt.mobile.spotify_stats.feature_top.data.remote.response.get_tracks_features.TracksFeaturesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TopApi {

    @GET("v1/me/top/tracks")
    suspend fun getTopItems(
        @Query("time_range") time_range: String,
        @Query("limit") limit: Int = 50
    ): Response<TopTracksResponse>

    @GET("v1/me/top/artists")
    suspend fun getTopArtists(
        @Query("time_range") time_range: String,
        @Query("limit") limit: Int = 50
    ): Response<TopArtistsResponse>

    @GET("v1/audio-features")
    suspend fun getTracksFeatures(
        @Query("ids") ids: String
    ): Response<TracksFeaturesResponse>

}