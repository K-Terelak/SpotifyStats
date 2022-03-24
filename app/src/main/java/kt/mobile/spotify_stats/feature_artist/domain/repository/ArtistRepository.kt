package kt.mobile.spotify_stats.feature_artist.domain.repository

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.core.data.dto.response.get_top_item.ArtistItem
import kt.mobile.spotify_stats.feature_artist.data.remote.response.ArtistsTopTracksResponse
import kt.mobile.spotify_stats.feature_artist.data.remote.response.get_related.RelatedArtistsResponse

interface ArtistRepository {

    suspend fun getArtist(id: String): Resource<ArtistItem>
    suspend fun getArtistsTopTracks(id: String): Resource<ArtistsTopTracksResponse>
    suspend fun getRelatedArtists(id: String): Resource<RelatedArtistsResponse>
}