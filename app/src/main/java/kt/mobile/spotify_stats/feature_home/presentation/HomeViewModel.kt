package kt.mobile.spotify_stats.feature_home.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.core.util.UiEvent
import kt.mobile.spotify_stats.feature_home.domain.use_case.HomeUseCases
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases,
) : ViewModel() {

    private val _homeState = mutableStateOf(HomeState())
    val homeState: State<HomeState> = _homeState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        loadData()
    }

    fun loadData() {
        getMyProfile()
        getCurrentlyPlaying()
        getRecentlyPlayed()
    }

    private fun getMyProfile() {
        viewModelScope.launch {
            _homeState.value = homeState.value.copy(
                isMyProfileLoading = true
            )
            when (val result = homeUseCases.getMyProfile()) {
                is Resource.Success -> {
                    Log.d("getMyProfile", "SUCCESS")
                    _homeState.value = homeState.value.copy(
                        myProfile = result.data,
                        isMyProfileLoading = false,
                        profileError = ""
                    )
                }
                is Resource.Error -> {
                    Log.e("getMyProfile", "ERROR ${result.error}")
                    _homeState.value = homeState.value.copy(
                        isMyProfileLoading = false,
                        profileError = result.error ?: "Unknown error"
                    )
                }
            }
        }
    }

    private fun getCurrentlyPlaying() {
        viewModelScope.launch {
            _homeState.value = homeState.value.copy(
                isCurrentlyPlayingLoading = true
            )
            when (val result = homeUseCases.getCurrentlyPlaying()) {
                is Resource.Success -> {
                    Log.d("getCurrentlyPlaying", "SUCCESS")
                    _homeState.value = homeState.value.copy(
                        currentlyPlaying = result.data,
                        isCurrentlyPlayingLoading = false,
                        currentlyPlayingError = ""
                    )
                }
                is Resource.Error -> {
                    Log.e("getCurrentlyPlaying", "ERROR ${result.error}")
                    _homeState.value = homeState.value.copy(
                        currentlyPlaying = null,
                        isCurrentlyPlayingLoading = false,
                        currentlyPlayingError = result.error ?: "Unknown error"
                    )
                }
            }
        }
    }

    private fun getRecentlyPlayed() {
        viewModelScope.launch {
            _homeState.value = homeState.value.copy(
                isRecentlyPlayedLoading = true
            )
            when (val result = homeUseCases.getRecentlyPlayed()) {
                is Resource.Success -> {
                    Log.d("getRecentlyPlayed", "SUCCESS")
                    _homeState.value = homeState.value.copy(
                        recentlyPlayed = result.data,
                        isRecentlyPlayedLoading = false,
                        recentlyPlayedError = ""
                    )
                }
                is Resource.Error -> {
                    Log.e("getRecentlyPlayed", "ERROR ${result.error}")
                    _homeState.value = homeState.value.copy(
                        isRecentlyPlayedLoading = false,
                        recentlyPlayedError = result.error ?: "Unknown error"
                    )
                }
            }
        }
    }
}