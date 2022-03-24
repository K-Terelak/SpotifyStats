package kt.mobile.spotify_stats.feature_global.domain.repository

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_global.data.remote.response.get_top_global.TopGlobalResponse

interface GlobalRepository {

    suspend fun getTopGlobal(id: String): Resource<TopGlobalResponse>
}