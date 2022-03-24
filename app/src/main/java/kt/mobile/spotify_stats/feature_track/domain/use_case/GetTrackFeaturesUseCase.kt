package kt.mobile.spotify_stats.feature_track.domain.use_case

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_top.domain.models.TracksFeatures
import kt.mobile.spotify_stats.feature_track.domain.repository.TrackRepository

class GetTrackFeaturesUseCase(
    private val repository: TrackRepository
) {
    suspend operator fun invoke(ids: String): Resource<TracksFeatures> {
        return repository.getTrackFeatures(ids = ids)
    }
}