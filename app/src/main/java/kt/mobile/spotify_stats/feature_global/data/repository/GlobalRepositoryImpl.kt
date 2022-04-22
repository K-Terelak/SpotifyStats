package kt.mobile.spotify_stats.feature_global.data.repository

import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_global.data.remote.GlobalApi
import kt.mobile.spotify_stats.feature_global.data.remote.response.get_top_global.TopGlobalResponse
import kt.mobile.spotify_stats.feature_global.domain.repository.GlobalRepository
import retrofit2.HttpException
import java.io.IOException

class GlobalRepositoryImpl(
    private val api: GlobalApi
) : GlobalRepository {

    override suspend fun getTopGlobal(id: String): Resource<TopGlobalResponse> {
        return try {

            val response = api.getTopGlobal(id = id)

            if (response.isSuccessful) {
                Resource.Success(data = response.body())
            } else {
                response.errorBody()?.let { error ->
                    Resource.Error(error = R.string.couldnt_load)
                } ?: Resource.Error(error = R.string.unknown_error)
            }
        } catch (e: IOException) {
            Resource.Error(error =R.string.couldnt_reach_server)

        } catch (e: HttpException) {
                Resource.Error(error = R.string.couldnt_load)
        }
    }
}