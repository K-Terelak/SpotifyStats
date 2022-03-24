package kt.mobile.spotify_stats.feature_home.domain.use_case

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_home.domain.models.RecentlyPlayed
import kt.mobile.spotify_stats.feature_home.domain.repository.HomeRepository

class GetRecentlyPlayedUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Resource<RecentlyPlayed> {
        return repository.getRecentlyPlayed()
    }
}