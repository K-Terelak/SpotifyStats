package kt.mobile.spotify_stats.feature_auth.data.repository

import android.content.SharedPreferences
import android.util.Log
import kt.mobile.spotify_stats.R
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
                Resource.Error(error = R.string.couldnt_get_token)
            }
        } catch (e: IOException) {
            Resource.Error(error = R.string.couldnt_reach_server)

        } catch (e: HttpException) {
            Resource.Error(error = R.string.couldnt_load)
        }
    }

    override suspend fun getRefreshAuthToken(): SimpleResource {
        return try {

            val refreshToken = sharedPreferences.getString(KEY_REFRESH_BEARER_TOKEN, "")
            if (refreshToken.isNullOrEmpty()) {
                return Resource.Error(error = R.string.not_logged_in)
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
                Resource.Error(error = R.string.couldnt_refresh_token)
            }
        } catch (e: IOException) {
            Resource.Error(error = R.string.couldnt_reach_server)

        } catch (e: HttpException) {
            Resource.Error(error =  R.string.couldnt_load)
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
            Resource.Error(error = R.string.couldnt_logout)
        }
    }
}