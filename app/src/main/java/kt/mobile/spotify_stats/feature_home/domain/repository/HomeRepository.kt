package kt.mobile.spotify_stats.feature_home.domain.repository

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_home.domain.models.CurrentlyPlaying
import kt.mobile.spotify_stats.feature_home.domain.models.MyProfile
import kt.mobile.spotify_stats.feature_home.domain.models.RecentlyPlayed

interface HomeRepository {

    suspend fun getMyProfile(): Resource<MyProfile>
    suspend fun getCurrentlyPlaying(): Resource<CurrentlyPlaying>
    suspend fun getRecentlyPlayed(): Resource<RecentlyPlayed>
}