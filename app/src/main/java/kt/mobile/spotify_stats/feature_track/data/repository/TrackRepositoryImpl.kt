package kt.mobile.spotify_stats.feature_track.data.repository

import kt.mobile.spotify_stats.R
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
                response.errorBody()?.let {
                    Resource.Error(error = R.string.couldnt_load)
                } ?: Resource.Error(error = R.string.unknown_error)
            }
        } catch (e: IOException) {
            Resource.Error(error = R.string.couldnt_reach_server)

        } catch (e: HttpException) {
            Resource.Error(error =  R.string.couldnt_load)
        }
    }

    override suspend fun getTrackFeatures(ids: String): Resource<TracksFeatures> {
        return try {

            val response = api.getTrackFeatures(ids = ids)

            if (response.isSuccessful) {
                Resource.Success(data = response.body()?.toTracksFeatures())
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