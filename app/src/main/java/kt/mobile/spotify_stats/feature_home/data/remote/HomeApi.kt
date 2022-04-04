package kt.mobile.spotify_stats.feature_home.data.remote

import kt.mobile.spotify_stats.feature_home.data.remote.response.get_current_playing.CurrentPlayingResponse
import kt.mobile.spotify_stats.feature_home.data.remote.response.get_profile.MyProfileResponse
import kt.mobile.spotify_stats.feature_home.data.remote.response.get_recently_played.RecentlyPlayedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    @GET("/v1/me")
    suspend fun getMyProfile(): Response<MyProfileResponse>

    @GET("v1/me/player/currently-playing")
    suspend fun getCurrentlyPlaying(): Response<CurrentPlayingResponse>

    @GET("v1/me/player/recently-played")
    suspend fun getRecentlyPlayed(
        @Query("limit") limit: Int = 50
    ): Response<RecentlyPlayedResponse>

}