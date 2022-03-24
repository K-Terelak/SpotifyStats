package kt.mobile.spotify_stats.feature_global.data.repository

import android.util.Log
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
                    Resource.Error(error = "Couldn't load: $error")
                } ?: Resource.Error(error = "Unknown Error")
            }
        } catch (e: IOException) {
            Log.e("getTopGlobal", "IOException $e")
            Resource.Error(
                error = "Oops! Couldn\'t reach server. Check your internet connection"
            )
        } catch (e: HttpException) {
            Log.e("getTopGlobal", "HttpException ${e.message()}")
            Resource.Error(
                error = e.message.toString()
            )
        }
    }
}