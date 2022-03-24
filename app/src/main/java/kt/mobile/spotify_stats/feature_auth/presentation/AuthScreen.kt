package kt.mobile.spotify_stats.feature_auth.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.collectLatest
import kt.mobile.spotify_stats.core.presentation.components.CenteredCircularProgress
import kt.mobile.spotify_stats.core.util.UiEvent
import kt.mobile.spotify_stats.feature_auth.presentation.components.Login
import kt.mobile.spotify_stats.feature_auth.presentation.components.NoInternet

@Composable
fun AuthScreen(
    scaffoldState: ScaffoldState,
    authViewModel: AuthViewModel,
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        authViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (authViewModel.authState.value.isLoading) {
            CenteredCircularProgress()
        } else {
            if (authViewModel.authState.value.isRefresh) {
                NoInternet(
                    authViewModel = authViewModel,
                )
            } else {
                Login(
                    authViewModel = authViewModel,
                    context = context
                )
            }
        }
    }
}