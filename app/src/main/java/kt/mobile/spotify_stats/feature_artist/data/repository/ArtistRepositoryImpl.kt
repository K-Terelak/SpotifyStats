package kt.mobile.spotify_stats.feature_artist.data.repository

import android.content.SharedPreferences
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.core.data.dto.response.get_top_item.ArtistItem
import kt.mobile.spotify_stats.feature_artist.data.remote.ArtistApi
import kt.mobile.spotify_stats.feature_artist.data.remote.response.ArtistsTopTracksResponse
import kt.mobile.spotify_stats.feature_artist.data.remote.response.get_related.RelatedArtistsResponse
import kt.mobile.spotify_stats.feature_artist.domain.repository.ArtistRepository
import retrofit2.HttpException
import java.io.IOException

class ArtistRepositoryImpl(
    private val api: ArtistApi,
    private val sharedPreferences: SharedPreferences,
) : ArtistRepository {


    override suspend fun getArtist(id: String): Resource<ArtistItem> {
        return try {

            val response = api.getArtist(id = id)

            if (response.isSuccessful) {
                response.body()?.let { body->
                    Resource.Success(data = body)
                } ?: Resource.Error(R.string.empty)
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

    override suspend fun getArtistsTopTracks(id: String): Resource<ArtistsTopTracksResponse> {
        return try {

            val market = sharedPreferences.getString("country", "") ?: ""
            if (market.isEmpty()) {
                return Resource.Error(error = R.string.no_country_code)
            } else {
                val response = api.getArtistTopTracks(id = id, market = market)

                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.Success(data = response.body())
                    } ?: Resource.Error(R.string.empty)
                } else {
                    response.errorBody()?.let {
                        Resource.Error(error = R.string.couldnt_load)
                    } ?: Resource.Error(error = R.string.unknown_error)
                }
            }
        } catch (e: IOException) {
            Resource.Error(error = R.string.couldnt_reach_server)

        } catch (e: HttpException) {
            Resource.Error(error = R.string.couldnt_load)
        }
    }

    override suspend fun getRelatedArtists(id: String): Resource<RelatedArtistsResponse> {
        return try {
            val response = api.getRelatedArtists(id = id)

            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(data = response.body())
                } ?: Resource.Error(R.string.empty)
            } else {
                response.errorBody()?.let {
                    Resource.Error(R.string.couldnt_load)
                } ?: Resource.Error(error = R.string.unknown_error)
            }
        } catch (e: IOException) {
            Resource.Error(
                error = R.string.couldnt_reach_server
            )
        } catch (e: HttpException) {
            Resource.Error(
                error = R.string.couldnt_load
            )
        }
    }
}