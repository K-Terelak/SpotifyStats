package kt.mobile.spotify_stats.feature_top.data.repository

import android.util.Log
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.core.domain.models.TopArtists
import kt.mobile.spotify_stats.feature_top.data.remote.TopApi
import kt.mobile.spotify_stats.feature_top.domain.models.TopTracks
import kt.mobile.spotify_stats.feature_top.domain.models.TracksFeatures
import kt.mobile.spotify_stats.feature_top.domain.repository.TopRepository
import retrofit2.HttpException
import java.io.IOException

class TopRepositoryImpl(
    private val api: TopApi,
) : TopRepository {


    override suspend fun getTopItems(time_range: String): Resource<TopTracks> {
        return try {

            val response = api.getTopItems(time_range = time_range)

            if (response.isSuccessful) {
                Resource.Success(data = response.body()?.toTopTracks())
            } else {
                response.errorBody()?.let { error ->
                    Resource.Error(error = "Couldn't load: $error")
                } ?: Resource.Error(error = "Unknown Error")
            }
        } catch (e: IOException) {
            Log.e("getTopItems", "IOException $e")
            Resource.Error(
                error = "Oops! Couldn\'t reach server. Check your internet connection"
            )
        } catch (e: HttpException) {
            Log.e("getTopItems", "HttpException ${e.message()}")
            Resource.Error(
                error = e.message.toString()
            )
        }
    }

    override suspend fun getTracksFeatures(ids: String): Resource<TracksFeatures> {
        return try {

            val response = api.getTracksFeatures(ids = ids)

            if (response.isSuccessful) {
                Resource.Success(data = response.body()?.toTracksFeatures())
            } else {
                response.errorBody()?.let { error ->
                    Resource.Error(error = "Couldn't load: $error")
                } ?: Resource.Error(error = "Unknown Error")
            }
        } catch (e: IOException) {
            Log.e("getTracksFeatures", "IOException $e")
            Resource.Error(
                error = "Oops! Couldn\'t reach server. Check your internet connection"
            )
        } catch (e: HttpException) {
            Log.e("getTracksFeatures", "HttpException ${e.message()}")
            Resource.Error(
                error = e.message.toString()
            )
        }
    }

    override suspend fun getTopArtists(time_range: String): Resource<TopArtists> {
        return try {

            val response = api.getTopArtists(time_range = time_range)

            if (response.isSuccessful) {
                Resource.Success(data = response.body()?.toTopArtists())
            } else {
                response.errorBody()?.let { error ->
                    Resource.Error(error = "Couldn't load: $error")
                } ?: Resource.Error(error = "Unknown Error")
            }
        } catch (e: IOException) {
            Log.e("getTopArtists", "IOException $e")
            Resource.Error(
                error = "Oops! Couldn\'t reach server. Check your internet connection"
            )
        } catch (e: HttpException) {
            Log.e("getTopArtists", "HttpException ${e.message()}")
            Resource.Error(
                error = e.message.toString()
            )
        }
    }
}