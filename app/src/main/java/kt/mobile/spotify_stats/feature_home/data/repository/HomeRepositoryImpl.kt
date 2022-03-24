package kt.mobile.spotify_stats.feature_home.data.repository

import android.content.SharedPreferences
import android.util.Log
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_home.data.remote.HomeApi
import kt.mobile.spotify_stats.feature_home.domain.models.CurrentlyPlaying
import kt.mobile.spotify_stats.feature_home.domain.models.MyProfile
import kt.mobile.spotify_stats.feature_home.domain.models.RecentlyPlayed
import kt.mobile.spotify_stats.feature_home.domain.repository.HomeRepository
import retrofit2.HttpException
import java.io.IOException

class HomeRepositoryImpl(
    private val api: HomeApi,
    private val sharedPreferences: SharedPreferences,
) : HomeRepository {


    override suspend fun getMyProfile(): Resource<MyProfile> {
        return try {

            val response = api.getMyProfile()

            if (response.isSuccessful) {
                val result = response.body()?.toMyProfile()
                sharedPreferences.edit()
                    .putString("country", result?.country)
                    .apply()
                Resource.Success(data = result)
            } else {
                response.errorBody()?.let { error ->
                    Resource.Error(error = "Couldn't load: $error")
                } ?: Resource.Error(error = "Unknown Error")
            }
        } catch (e: IOException) {
            Log.e("getMyProfile", "IOException $e")
            Resource.Error(
                error = "Oops! Couldn\'t reach server. Check your internet connection"
            )
        } catch (e: HttpException) {
            Log.e("getMyProfile", "HttpException ${e.message()}")
            Resource.Error(
                error = e.message.toString()
            )
        }
    }

    override suspend fun getCurrentlyPlaying(): Resource<CurrentlyPlaying> {
        return try {

            val response = api.getCurrentlyPlaying()

            if (response.isSuccessful) {
                if (response.body()?.currentlyPlayingType == "ad")
                    return Resource.Error(error = "AD")

                if (response.body()?.isPlaying == false)
                    return Resource.Error("Nothing")

                response.body()?.let {
                    Resource.Success(data = it.toCurrentlyPlaying())
                } ?: Resource.Error(error = "empty")
            } else {
                response.errorBody()?.let { error ->
                    Resource.Error(error = "Couldn't load: $error")
                } ?: Resource.Error(error = "Unknown Error")
            }
        } catch (e: IOException) {
            Log.e("getCurrentlyPlaying", "IOException $e")
            Resource.Error(
                error = "Oops! Couldn\'t reach server. Check your internet connection"
            )
        } catch (e: HttpException) {
            Log.e("getCurrentlyPlaying", "HttpException ${e.message()}")
            Resource.Error(
                error = e.message.toString()
            )
        }
    }

    override suspend fun getRecentlyPlayed(): Resource<RecentlyPlayed> {
        return try {

            val response = api.getRecentlyPlayed()

            if (response.isSuccessful) {
                Resource.Success(data = response.body()?.toRecentlyPlayed())
            } else {
                response.errorBody()?.let { error ->
                    Resource.Error(error = "Couldn't load: $error")
                } ?: Resource.Error(error = "Unknown Error")
            }
        } catch (e: IOException) {
            Log.e("getRecentlyPlayed", "IOException $e")
            Resource.Error(
                error = "Oops! Couldn\'t reach server. Check your internet connection"
            )
        } catch (e: HttpException) {
            Log.e("getRecentlyPlayed", "IOException${e.message()}")
            Resource.Error(
                error = e.message.toString()
            )
        }
    }
}