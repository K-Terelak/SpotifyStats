package kt.mobile.spotify_stats.feature_auth.data.remote.response


import com.google.gson.annotations.SerializedName

data class AuthRefreshTokenResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("scope")
    val scope: String,
    @SerializedName("token_type")
    val tokenType: String
)