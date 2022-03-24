package kt.mobile.spotify_stats.feature_track.domain.repository

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_top.domain.models.TracksFeatures
import kt.mobile.spotify_stats.feature_track.domain.models.MyTrack

interface TrackRepository {

    suspend fun getTrack(id: String): Resource<MyTrack>
    suspend fun getTrackFeatures(ids: String): Resource<TracksFeatures>
}