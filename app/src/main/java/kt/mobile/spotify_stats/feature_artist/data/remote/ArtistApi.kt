package kt.mobile.spotify_stats.feature_artist.data.remote

import kt.mobile.spotify_stats.core.data.dto.response.get_top_item.ArtistItem
import kt.mobile.spotify_stats.feature_artist.data.remote.response.ArtistsTopTracksResponse
import kt.mobile.spotify_stats.feature_artist.data.remote.response.get_related.RelatedArtistsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtistApi {

    @GET("v1/artists/{id}")
    suspend fun getArtist(
        @Path("id") id: String
    ): Response<ArtistItem>


    @GET("v1/artists/{id}/top-tracks")
    suspend fun getArtistTopTracks(
        @Path("id") id: String,
        @Query("market") market: String,
    ): Response<ArtistsTopTracksResponse>


    @GET("v1/artists/{id}/related-artists")
    suspend fun getRelatedArtists(
        @Path("id") id: String,
    ): Response<RelatedArtistsResponse>

}