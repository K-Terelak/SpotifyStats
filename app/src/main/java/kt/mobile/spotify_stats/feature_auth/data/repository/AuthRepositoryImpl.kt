package kt.mobile.spotify_stats.feature_auth.data.repository

import android.content.SharedPreferences
import android.util.Log
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.core.data.SimpleResource
import kt.mobile.spotify_stats.core.util.Constants.KEY_BEARER_TOKEN
import kt.mobile.spotify_stats.core.util.Constants.KEY_REFRESH_BEARER_TOKEN
import kt.mobile.spotify_stats.feature_auth.data.remote.AuthApi
import kt.mobile.spotify_stats.feature_auth.data.remote.response.AuthTokenResponse
import kt.mobile.spotify_stats.feature_auth.domain.repository.AuthRepository
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {


    override suspend fun getAuthToken(code: String): SimpleResource {
        return try {

            val response = api.getAuthToken(code = code)

            if (response.isSuccessful) {
                response.body()?.let { success: AuthTokenResponse ->
                    sharedPreferences.edit()
                        .putString(KEY_BEARER_TOKEN, success.accessToken)
                        .putString(KEY_REFRESH_BEARER_TOKEN, success.refreshToken)
                        .apply()
                }
                Resource.Success(Unit)
            } else {
                Log.e("getAuthToken", "${response.errorBody()}")
                Resource.Error(error = "Couldn't get token")
            }
        } catch (e: IOException) {
            Log.e("getAuthToken", "IOException $e")
            Resource.Error(
                error = "Oops! Couldn\'t reach server. Check your internet connection"
            )
        } catch (e: HttpException) {
            Log.e("getAuthToken", "HttpException ${e.message()}")
            Resource.Error(
                error = e.message.toString()
            )
        }
    }

    override suspend fun getRefreshAuthToken(): SimpleResource {
        return try {

            val refreshToken = sharedPreferences.getString(KEY_REFRESH_BEARER_TOKEN, "")
            if (refreshToken.isNullOrEmpty()) {
                return Resource.Error(error = "Not logged in")
            }

            val response = api.getRefreshAuthToken(refresh_token = refreshToken)

            if (response.isSuccessful) {
                response.body()?.let { authResponse ->
                    sharedPreferences.edit()
                        .putString(KEY_BEARER_TOKEN, authResponse.accessToken)
                        .apply()
                }
                Resource.Success(Unit)
            } else {
                Log.e("getRefreshAuthToken", "${response.errorBody()}")
                Resource.Error(error = "Couldn't refresh token")
            }
        } catch (e: IOException) {
            Log.e("getRefreshAuthToken", "IOException $e")
            Resource.Error(
                error = "Oops! Couldn\'t reach server. Check your internet connection"
            )
        } catch (e: HttpException) {
            Log.e("getRefreshAuthToken", "HttpException ${e.message()}")
            Resource.Error(
                error = e.message.toString()
            )
        }
    }

    override suspend fun logout(): SimpleResource {
        return try {

            sharedPreferences.edit()
                .remove(KEY_BEARER_TOKEN)
                .remove(KEY_REFRESH_BEARER_TOKEN)
                .apply()

            Resource.Success(Unit)
        } catch (e: IOException) {
            Log.e("logout", "IOException $e")
            Resource.Error(error = "Could\'t logout")
        }
    }
}