package kt.mobile.spotify_stats.feature_global.domain.use_case

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_global.data.remote.response.get_top_global.TopGlobalResponse
import kt.mobile.spotify_stats.feature_global.domain.repository.GlobalRepository

class GetTopGlobalUseCase(
    private val repository: GlobalRepository
) {

    suspend operator fun invoke(id: String): Resource<TopGlobalResponse> {
        return repository.getTopGlobal(id = id)
    }
}