package kt.mobile.spotify_stats.feature_global.data.remote

import kt.mobile.spotify_stats.feature_global.data.remote.response.get_top_global.TopGlobalResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GlobalApi {

    @GET("v1/playlists/{id}")
    suspend fun getTopGlobal(
        @Path("id") id: String
    ): Response<TopGlobalResponse>

}