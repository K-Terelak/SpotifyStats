package kt.mobile.spotify_stats.feature_auth.presentation

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spotify.sdk.android.auth.AuthorizationClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.core.util.UiEvent
import kt.mobile.spotify_stats.feature_auth.domain.use_case.AuthUseCases
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    private val _auth = mutableStateOf(AuthState())
    val authState: State<AuthState> = _auth

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        renewToken()
    }

    fun getToken(code: String) {
        viewModelScope.launch {
            when (val result = authUseCases.getAuthToken(code = code)) {
                is Resource.Success -> {
                    Log.d("getToken", "Resource.Success")
                    _auth.value = authState.value.copy(isLogged = true)
                }
                is Resource.Error -> {
                    Log.e("getToken", "Resource.Error ${result.error}")
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            text = result.error ?: R.string.unknown_error
                        )
                    )
                }
            }
        }
    }

    fun renewToken() {
        viewModelScope.launch {
            when (val result = authUseCases.getRefreshToken()) {
                is Resource.Success -> {
                    Log.d("renewToken", "Success")
                    _auth.value = authState.value.copy(
                        isLogged = true,
                        isLoading = false,
                        isRefresh = false,
                    )
                }
                is Resource.Error -> {
                    Log.e("renewToken", "Error: ${result.error}")
                    if (result.error == R.string.couldnt_reach_server) {
                        _auth.value = authState.value.copy(
                            isLoading = false,
                            isRefresh = true
                        )
                    } else {
                        _auth.value = authState.value.copy(
                            isLoading = false,
                            isRefresh = false
                        )
                    }
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            text = result.error ?: R.string.unknown_error
                        )
                    )
                }
            }
        }
    }

    fun logout(context: Activity?) {
        viewModelScope.launch {
            when (val result = authUseCases.logout()) {
                is Resource.Success -> {
                    Log.d("logout", "SUCCESS")
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            text = R.string.successfully_logged_out
                        )
                    )
                    _auth.value = authState.value.copy(
                        isLogged = false,
                        isRefresh = false
                    )
                    AuthorizationClient.clearCookies(context)
                    context?.finish()
                }
                is Resource.Error -> {
                    Log.e("logout", "ERROR")
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            text = result.error ?: R.string.unknown_error
                        )
                    )
                }
            }
        }
    }
}