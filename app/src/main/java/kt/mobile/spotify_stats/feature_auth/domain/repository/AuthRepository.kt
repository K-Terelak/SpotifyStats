package kt.mobile.spotify_stats.feature_auth.domain.repository

import kt.mobile.spotify_stats.core.data.SimpleResource


interface AuthRepository {
    suspend fun getAuthToken(code: String): SimpleResource
    suspend fun getRefreshAuthToken(): SimpleResource
    suspend fun logout(): SimpleResource
}