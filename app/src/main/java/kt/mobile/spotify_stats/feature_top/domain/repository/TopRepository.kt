package kt.mobile.spotify_stats.feature_top.domain.repository

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.core.domain.models.TopArtists
import kt.mobile.spotify_stats.feature_top.domain.models.TopTracks
import kt.mobile.spotify_stats.feature_top.domain.models.TracksFeatures

interface TopRepository {

    suspend fun getTopArtists(time_range: String): Resource<TopArtists>
    suspend fun getTopItems(time_range: String): Resource<TopTracks>
    suspend fun getTracksFeatures(ids: String): Resource<TracksFeatures>
}