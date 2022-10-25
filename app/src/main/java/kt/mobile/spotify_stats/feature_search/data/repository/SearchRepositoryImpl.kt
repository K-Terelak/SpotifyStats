package kt.mobile.spotify_stats.feature_search.data.repository

import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_search.data.remote.SearchApi
import kt.mobile.spotify_stats.feature_search.domain.models.MySearchItems
import kt.mobile.spotify_stats.feature_search.domain.repository.SearchRepository
import retrofit2.HttpException
import java.io.IOException

class SearchRepositoryImpl(
    private val api: SearchApi,
) : SearchRepository {

    override suspend fun searchArtists(q: String): Resource<MySearchItems> = try {

        val response = api.searchArtists(q = q)

        if (response.isSuccessful) {
            Resource.Success(data = response.body()?.toMySearchItems())
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

    override suspend fun searchTracks(q: String): Resource<MySearchItems> = try {

        val response = api.searchTracks(q = q)

        if (response.isSuccessful) {
            Resource.Success(data = response.body()?.toMySearchItems())
        } else {
            response.errorBody()?.let {
                Resource.Error(error = R.string.couldnt_load)
            } ?: Resource.Error(error = R.string.unknown_error)
        }
    } catch (e: IOException) {
        Resource.Error(
            error = R.string.couldnt_reach_server
        )
    } catch (e: HttpException) {
        Resource.Error(error = R.string.couldnt_load)
    }
}
