package kt.mobile.spotify_stats.feature_auth.presentation

data class AuthState(
    val isLogged: Boolean = false,
    val isLoading: Boolean = true,
    val isRefresh: Boolean = false
)
