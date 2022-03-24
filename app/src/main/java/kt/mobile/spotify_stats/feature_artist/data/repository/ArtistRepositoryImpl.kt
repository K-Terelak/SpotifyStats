package kt.mobile.spotify_stats.feature_artist.data.repository

import android.content.SharedPreferences
import android.util.Log
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
                Resource.Success(data = response.body())
            } else {
                response.errorBody()?.let { error ->
                    Resource.Error(error = "Couldn't load: $error")
                } ?: Resource.Error(error = "Unknown Error")
            }
        } catch (e: IOException) {
            Log.e("getArtist", "IOException $e")
            Resource.Error(
                error = "Oops! Couldn\'t reach server. Check your internet connection"
            )
        } catch (e: HttpException) {
            Log.e("getArtist", "HttpException ${e.message()}")
            Resource.Error(
                error = e.message.toString()
            )
        }
    }

    override suspend fun getArtistsTopTracks(id: String): Resource<ArtistsTopTracksResponse> {
        return try {

            val market = sharedPreferences.getString("country", "") ?: ""
            if (market.isEmpty()) {
                return Resource.Error(error = "No country code")
            } else {
                val response = api.getArtistTopTracks(id = id, market = market)

                if (response.isSuccessful) {
                    Resource.Success(data = response.body())
                } else {
                    response.errorBody()?.let { error ->
                        Resource.Error(error = "Couldn't load: $error")
                    } ?: Resource.Error(error = "Unknown Error")
                }
            }
        } catch (e: IOException) {
            Log.e("getArtistsTopTracks", "IOException $e")
            Resource.Error(
                error = "Oops! Couldn\'t reach server. Check your internet connection"
            )
        } catch (e: HttpException) {
            Log.e("getArtistsTopTracks", "HttpException ${e.message()}")
            Resource.Error(
                error = e.message.toString()
            )
        }
    }

    override suspend fun getRelatedArtists(id: String): Resource<RelatedArtistsResponse> {
        return try {
            val response = api.getRelatedArtists(id = id)

            if (response.isSuccessful) {
                Resource.Success(data = response.body())
            } else {
                response.errorBody()?.let { error ->
                    Resource.Error(error = "Couldn't load: $error")
                } ?: Resource.Error(error = "Unknown Error")
            }
        } catch (e: IOException) {
            Log.e("getRelatedArtists", "IOException $e")
            Resource.Error(
                error = "Oops! Couldn\'t reach server. Check your internet connection"
            )
        } catch (e: HttpException) {
            Log.e("getRelatedArtists", "HttpException ${e.message()}")
            Resource.Error(
                error = e.message.toString()
            )
        }
    }
}