package kt.mobile.spotify_stats.feature_search.data.remote

import kt.mobile.spotify_stats.feature_search.data.remote.response.search_artists.SearchArtistsResponse
import kt.mobile.spotify_stats.feature_search.data.remote.response.search_tracks.SearchTracksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("v1/search")
    suspend fun searchArtists(
        @Query("type") type: String = "artist",
        @Query("q") q: String,
        @Query("limit") limit: Int = 6
    ): Response<SearchArtistsResponse>

    @GET("v1/search")
    suspend fun searchTracks(
        @Query("type") type: String = "track",
        @Query("q") q: String,
        @Query("limit") limit: Int = 6
    ): Response<SearchTracksResponse>

}