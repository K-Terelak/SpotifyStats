package kt.mobile.spotify_stats.feature_track.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.core.util.Constants.TRACK_ID
import kt.mobile.spotify_stats.feature_track.domain.use_case.TrackUseCases
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val trackUseCases: TrackUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _trackState = mutableStateOf(TrackState())
    val trackState: State<TrackState> = _trackState


    init {
        savedStateHandle.get<String>(TRACK_ID)?.let { trackId ->
            getTrack(trackId = trackId)
            getTrackFeatures(trackId = trackId)
        }
    }

    private fun getTrack(trackId: String) {
        viewModelScope.launch {

            _trackState.value = trackState.value.copy(isTrackLoading = true)

            when (val result = trackUseCases.getTrack(trackId)) {
                is Resource.Success -> {
                    _trackState.value = trackState.value.copy(
                        track = result.data,
                        isTrackLoading = false,
                        trackError = null
                    )
                }
                is Resource.Error -> {
                    _trackState.value = trackState.value.copy(
                        isTrackLoading = false,
                        trackError = result.error
                    )

                }
            }
        }
    }

    private fun getTrackFeatures(trackId: String) {
        viewModelScope.launch {

            _trackState.value = trackState.value.copy(isTrackFeaturesLoading = true)

            when (val result = trackUseCases.getTrackFeatures(trackId)) {
                is Resource.Success -> {
                    _trackState.value = trackState.value.copy(
                        trackFeatures = result.data,
                        isTrackFeaturesLoading = false,
                        trackFeaturesError = null
                    )
                }
                is Resource.Error -> {
                    _trackState.value = trackState.value.copy(
                        isTrackFeaturesLoading = false,
                        trackFeaturesError = result.error
                    )
                }
            }
        }
    }
}