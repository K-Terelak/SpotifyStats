package kt.mobile.spotify_stats.feature_top.domain.use_case

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_top.domain.models.TopTracks
import kt.mobile.spotify_stats.feature_top.domain.repository.TopRepository

class GetTopItemsUseCase(
    private val repository: TopRepository
) {
    suspend operator fun invoke(time_range: String): Resource<TopTracks> {
        return repository.getTopItems(time_range = time_range)
    }
}