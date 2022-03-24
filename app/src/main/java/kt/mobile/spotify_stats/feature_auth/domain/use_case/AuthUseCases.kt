package kt.mobile.spotify_stats.feature_auth.domain.use_case


data class AuthUseCases(
    val getAuthToken: GetAuthTokenUseCase,
    val getRefreshToken: GetRefreshTokenUseCase,
    val logout: LogoutUseCase
)