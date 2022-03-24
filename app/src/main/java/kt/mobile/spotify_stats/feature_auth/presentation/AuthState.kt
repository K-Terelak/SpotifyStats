package kt.mobile.spotify_stats.feature_auth.presentation

import kt.mobile.spotify_stats.nav.Screen

data class AuthState(
    val startDestination: Screen? = null,
    val isLogged: Boolean = false,
    val isLoading: Boolean = true,
    val isRefresh: Boolean = false
)
