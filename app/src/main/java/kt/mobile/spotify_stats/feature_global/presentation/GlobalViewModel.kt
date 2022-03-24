package kt.mobile.spotify_stats.feature_global.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_global.domain.use_case.GlobalUseCases
import kt.mobile.spotify_stats.feature_global.util.Constants.TOP_50_WORLD_ALBUM
import javax.inject.Inject

@HiltViewModel
class GlobalViewModel @Inject constructor(
    private val globalUseCases: GlobalUseCases
) : ViewModel() {


    private val _globalState = mutableStateOf(GlobalState())
    val globalState: State<GlobalState> = _globalState

    init {
        getTopGlobal(TOP_50_WORLD_ALBUM)
    }

    private fun getTopGlobal(id: String) {
        viewModelScope.launch {

            _globalState.value = globalState.value.copy(isTracksLoading = true)

            when (val result = globalUseCases.getTopGlobal(id)) {
                is Resource.Success -> {
                    Log.d("getArtistTopTracks", "SUCCESS")
                    _globalState.value = globalState.value.copy(
                        tracks = result.data?.tracks?.items,
                        isTracksLoading = false
                    )
                }
                is Resource.Error -> {
                    Log.e("getArtistTopTracks", "ERROR ${result.error}")
                    _globalState.value = globalState.value.copy(
                        isTracksLoading = false,
                        isTracksError = result.error ?: "Unknown error"
                    )
                }
            }
        }
    }
}