package kt.mobile.spotify_stats.feature_top.domain.use_case

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_top.domain.models.TracksFeatures
import kt.mobile.spotify_stats.feature_top.domain.repository.TopRepository

class GetTracksFeaturesUseCase(
    private val repository: TopRepository
) {
    suspend operator fun invoke(ids: String): Resource<TracksFeatures> {
        return repository.getTracksFeatures(ids = ids)
    }
}