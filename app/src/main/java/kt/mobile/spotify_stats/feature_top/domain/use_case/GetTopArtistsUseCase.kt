package kt.mobile.spotify_stats.feature_top.domain.use_case

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.core.domain.models.TopArtists
import kt.mobile.spotify_stats.feature_top.domain.repository.TopRepository

class GetTopArtistsUseCase(
    private val repository: TopRepository
) {
    suspend operator fun invoke(time_range: String): Resource<TopArtists> {
        return repository.getTopArtists(time_range = time_range)
    }
}