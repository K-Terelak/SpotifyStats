package kt.mobile.spotify_stats.feature_home.data.repository

import android.content.SharedPreferences
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.core.data.SimpleResource
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

    override suspend fun getMyProfile(): Resource<MyProfile> = try {

        val response = api.getMyProfile()

        if (response.isSuccessful) {
            val result = response.body()?.toMyProfile()
            sharedPreferences.edit()
                .putString("country", result?.country)
                .apply()
            Resource.Success(data = result)
        } else {
            response.errorBody()?.let {
                Resource.Error(error = R.string.couldnt_load)
            } ?: Resource.Error(error = R.string.unknown_error)
        }
    } catch (e: IOException) {
        Resource.Error(error = R.string.couldnt_reach_server)

    } catch (e: HttpException) {
        Resource.Error(error = R.string.couldnt_load)
    }

    override suspend fun getCurrentlyPlaying(): Resource<CurrentlyPlaying> = try {

        val response = api.getCurrentlyPlaying()

        if (response.isSuccessful) {
            if (response.body()?.currentlyPlayingType == "ad") {
                Resource.Error<SimpleResource>(error = R.string.ad)
            }

            if (response.body()?.isPlaying == false)
                Resource.Error<SimpleResource>(R.string.nothing)

            response.body()?.let {
                Resource.Success(data = it.toCurrentlyPlaying())
            } ?: Resource.Error(error = R.string.empty)
        } else {
            response.errorBody()?.let {
                Resource.Error(error = R.string.couldnt_load)
            } ?: Resource.Error(error = R.string.unknown_error)
        }
    } catch (e: IOException) {
        Resource.Error(error = R.string.couldnt_reach_server)

    } catch (e: HttpException) {
        Resource.Error(error = R.string.couldnt_load)
    }

    override suspend fun getRecentlyPlayed(): Resource<RecentlyPlayed> = try {

        val response = api.getRecentlyPlayed()

        if (response.isSuccessful) {
            Resource.Success(data = response.body()?.toRecentlyPlayed())
        } else {
            response.errorBody()?.let {
                Resource.Error(error = R.string.couldnt_load)
            } ?: Resource.Error(error = R.string.unknown_error)
        }
    } catch (e: IOException) {
        Resource.Error(error = R.string.couldnt_reach_server)

    } catch (e: HttpException) {
        Resource.Error(error = R.string.couldnt_load)
    }
}
