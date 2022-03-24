package kt.mobile.spotify_stats.feature_track.domain.use_case

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_track.domain.models.MyTrack
import kt.mobile.spotify_stats.feature_track.domain.repository.TrackRepository

class GetTrackUseCase(
    private val repository: TrackRepository
) {
    suspend operator fun invoke(id: String): Resource<MyTrack> {
        return repository.getTrack(id = id)
    }
}