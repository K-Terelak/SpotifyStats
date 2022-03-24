package kt.mobile.spotify_stats.feature_track.data.repository

import android.util.Log
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_top.domain.models.TracksFeatures
import kt.mobile.spotify_stats.feature_track.data.remote.TrackApi
import kt.mobile.spotify_stats.feature_track.domain.models.MyTrack
import kt.mobile.spotify_stats.feature_track.domain.repository.TrackRepository
import retrofit2.HttpException
import java.io.IOException

class TrackRepositoryImpl(
    private val api: TrackApi,
) : TrackRepository {

    override suspend fun getTrack(id: String): Resource<MyTrack> {
        return try {

            val response = api.getTrack(id = id)

            if (response.isSuccessful) {
                Resource.Success(data = response.body()?.toTrack())
            } else {
                response.errorBody()?.let { error ->
                    Resource.Error(error = "Couldn't load: $error")
                } ?: Resource.Error(error = "Unknown Error")
            }
        } catch (e: IOException) {
            Log.e("getTrack", "IOException $e")
            Resource.Error(
                error = "Oops! Couldn\'t reach server. Check your internet connection"
            )
        } catch (e: HttpException) {
            Log.e("getTrack", "HttpException ${e.message()}")
            Resource.Error(
                error = e.message.toString()
            )
        }
    }

    override suspend fun getTrackFeatures(ids: String): Resource<TracksFeatures> {
        return try {

            val response = api.getTrackFeatures(ids = ids)

            if (response.isSuccessful) {
                Resource.Success(data = response.body()?.toTracksFeatures())
            } else {
                response.errorBody()?.let { error ->
                    Resource.Error(error = "Couldn't load: $error")
                } ?: Resource.Error(error = "Unknown Error")
            }
        } catch (e: IOException) {
            Log.e("getTrackFeatures", "IOException $e")
            Resource.Error(
                error = "Oops! Couldn\'t reach server. Check your internet connection"
            )
        } catch (e: HttpException) {
            Log.e("getTrackFeatures", "HttpException ${e.message()}")
            Resource.Error(
                error = e.message.toString()
            )
        }
    }
}