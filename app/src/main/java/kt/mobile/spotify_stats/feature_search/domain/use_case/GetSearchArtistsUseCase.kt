package kt.mobile.spotify_stats.feature_search.domain.use_case

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_search.domain.models.MySearchItems
import kt.mobile.spotify_stats.feature_search.domain.repository.SearchRepository

class GetSearchArtistsUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(q: String): Resource<MySearchItems> {
        return repository.searchArtists(q = q)
    }
}