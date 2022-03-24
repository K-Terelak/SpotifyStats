package kt.mobile.spotify_stats.feature_auth.domain.use_case

import kt.mobile.spotify_stats.core.data.SimpleResource
import kt.mobile.spotify_stats.feature_auth.domain.repository.AuthRepository

class LogoutUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): SimpleResource {
        return repository.logout()
    }
}