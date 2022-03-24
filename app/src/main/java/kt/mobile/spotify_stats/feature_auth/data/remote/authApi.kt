package kt.mobile.spotify_stats.feature_auth.data.remote

import kt.mobile.spotify_stats.feature_auth.data.remote.response.AuthRefreshTokenResponse
import kt.mobile.spotify_stats.feature_auth.data.remote.response.AuthTokenResponse
import kt.mobile.spotify_stats.feature_auth.util.Constants.AUTHORIZATION_CODE
import kt.mobile.spotify_stats.feature_auth.util.Constants.REDIRECT_URL
import kt.mobile.spotify_stats.feature_auth.util.Constants.REFRESH_TOKEN
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface AuthApi {

    @FormUrlEncoded
    @POST("api/token")
    suspend fun getAuthToken(
        @Field("grant_type")
        grant_type: String = AUTHORIZATION_CODE,
        @Field("code")
        code: String,
        @Field("redirect_uri")
        redirect_uri: String = REDIRECT_URL,
    ): Response<AuthTokenResponse>

    @FormUrlEncoded
    @POST("api/token")
    suspend fun getRefreshAuthToken(
        @Field("grant_type")
        grant_type: String = REFRESH_TOKEN,
        @Field("redirect_uri")
        redirect_uri: String = REDIRECT_URL,
        @Field("refresh_token")
        refresh_token: String
    ): Response<AuthRefreshTokenResponse>


    companion object {
        const val BASE_URL = "https://accounts.spotify.com/"
    }

}