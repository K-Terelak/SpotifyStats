package kt.mobile.spotify_stats.feature_search.domain.repository

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_search.domain.models.MySearchItems

interface SearchRepository {
    suspend fun searchArtists(q: String): Resource<MySearchItems>
    suspend fun searchTracks(q: String): Resource<MySearchItems>
}