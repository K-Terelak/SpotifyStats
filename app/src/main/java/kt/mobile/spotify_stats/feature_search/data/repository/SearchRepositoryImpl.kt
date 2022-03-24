package kt.mobile.spotify_stats.feature_search.data.repository

import android.util.Log
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_search.data.remote.SearchApi
import kt.mobile.spotify_stats.feature_search.domain.models.MySearchItems
import kt.mobile.spotify_stats.feature_search.domain.repository.SearchRepository
import retrofit2.HttpException
import java.io.IOException

class SearchRepositoryImpl(
    private val api: SearchApi,
) : SearchRepository {

    override suspend fun searchArtists(q: String): Resource<MySearchItems> {
        return try {

            val response = api.searchArtists(q = q)

            if (response.isSuccessful) {
                Resource.Success(data = response.body()?.toMySearchItems())
            } else {
                response.errorBody()?.let {
                    Resource.Error(error = "Couldn't load:")
                } ?: Resource.Error(error = "Unknown Error")
            }
        } catch (e: IOException) {
            Log.e("searchArtists", "IOException $e")
            Resource.Error(
                error = "Oops! Couldn\'t reach server. Check your internet connection"
            )
        } catch (e: HttpException) {
            Log.e("searchArtists", "HttpException ${e.message()}")
            Resource.Error(error = "Couldn\'t load")
        }
    }

    override suspend fun searchTracks(q: String): Resource<MySearchItems> {
        return try {

            val response = api.searchTracks(q = q)

            if (response.isSuccessful) {
                Resource.Success(data = response.body()?.toMySearchItems())
            } else {
                response.errorBody()?.let {
                    Resource.Error(error = "Couldn't load")
                } ?: Resource.Error(error = "Unknown Error")
            }
        } catch (e: IOException) {
            Log.e("searchTracks", "IOException $e")
            Resource.Error(
                error = "Oops! Couldn\'t reach server. Check your internet connection"
            )
        } catch (e: HttpException) {
            Log.e("searchTracks", "HttpException ${e.message()}")
            Resource.Error(error = "Couldn't load")
        }
    }
}