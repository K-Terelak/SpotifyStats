package kt.mobile.spotify_stats.feature_home.domain.use_case

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_home.domain.models.CurrentlyPlaying
import kt.mobile.spotify_stats.feature_home.domain.repository.HomeRepository

class GetCurrentlyPlayingUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Resource<CurrentlyPlaying> {
        return repository.getCurrentlyPlaying()
    }
}