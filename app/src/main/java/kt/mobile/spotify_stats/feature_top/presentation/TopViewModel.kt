package kt.mobile.spotify_stats.feature_top.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_top.domain.use_case.TopUseCases
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor(
    private val topUseCases: TopUseCases,
) : ViewModel() {

    private val _topState = mutableStateOf(TopState())
    val topState: State<TopState> = _topState

    init {
        loadData()
    }

    fun loadData() {
        onEvent(_topState.value.termTypeEvent)
    }

    fun onEvent(event: TermTypeEvent) {
        when (event) {
            is TermTypeEvent.Short -> {
                Log.d("term changed", "short")
                _topState.value = topState.value.copy(termTypeEvent = event)

                if (_topState.value.short.tracksState.value.topTracksFeatures?.listFeatures.isNullOrEmpty())
                    getTopTracks(event.term, _topState.value.short.tracksState)
                if (_topState.value.short.artistsState.value.topArtistsList?.artists.isNullOrEmpty())
                    getTopArtists(event.term, _topState.value.short.artistsState)

            }
            is TermTypeEvent.Medium -> {
                Log.d("term changed", "medium")
                _topState.value = topState.value.copy(termTypeEvent = event)

                if (_topState.value.medium.tracksState.value.topTracksFeatures?.listFeatures.isNullOrEmpty())
                    getTopTracks(event.term, _topState.value.medium.tracksState)
                if (_topState.value.medium.artistsState.value.topArtistsList?.artists.isNullOrEmpty())
                    getTopArtists(event.term, _topState.value.medium.artistsState)

            }
            is TermTypeEvent.Long -> {
                Log.d("term changed", "long")
                _topState.value = topState.value.copy(termTypeEvent = event)

                if (_topState.value.long.tracksState.value.topTracksFeatures?.listFeatures.isNullOrEmpty())
                    getTopTracks(event.term, _topState.value.long.tracksState)
                if (_topState.value.long.artistsState.value.topArtistsList?.artists.isNullOrEmpty())
                    getTopArtists(event.term, _topState.value.long.artistsState)

            }
        }
    }


    private fun getTopArtists(time_range: String, length: MutableState<TopArtistsState>) {
        viewModelScope.launch {

            length.value = length.value.copy(isTopArtistsLoading = true)

            when (val result = topUseCases.getTopArtists(time_range = time_range)) {
                is Resource.Success -> {
                    Log.d("getTopArtists", "SUCCESS")
                    length.value = length.value.copy(
                        topArtistsList = result.data,
                        isTopArtistsLoading = false,
                        topArtistsError = null
                    )
                }
                is Resource.Error -> {
                    Log.e("getTopArtists", "ERROR")
                    length.value = length.value.copy(
                        topArtistsList = null,
                        isTopArtistsLoading = false,
                        topArtistsError = result.error
                    )
                }
            }
        }
    }

    private fun getTopTracks(time_range: String, topTracksState: MutableState<TopTracksState>) {
        viewModelScope.launch {
            var topTracksIds = ""
            topTracksState.value = topTracksState.value.copy(isTopTracksLoading = true)

            when (val result = topUseCases.getTopItems(time_range = time_range)) {
                is Resource.Success -> {
                    Log.d("getTopTracks", "SUCCESS")

                    topTracksState.value = topTracksState.value.copy(
                        topTracksList = result.data,
                        isTopTracksLoading = false,
                        topTracksError = null
                    )

                    result.data?.tracks?.forEach {
                        topTracksIds += "${it.id},"
                    }
                    getTracksFeatures(
                        ids = topTracksIds.dropLast(1),
                        topTracksState = topTracksState
                    )
                }
                is Resource.Error -> {
                    Log.e("getTopTracks", "ERROR")
                    topTracksState.value = topTracksState.value.copy(
                        topTracksList = null,
                        isTopTracksLoading = false,
                        topTracksError = result.error
                    )
                }
            }
        }
    }

    private fun getTracksFeatures(ids: String, topTracksState: MutableState<TopTracksState>) {
        viewModelScope.launch {

            topTracksState.value = topTracksState.value.copy(isTracksFeaturesLoading = true)

            when (val result = topUseCases.getTracksFeatures(ids = ids)) {
                is Resource.Success -> {
                    Log.d("getTracksFeatures", "SUCCESS")

                    topTracksState.value = topTracksState.value.copy(
                        topTracksFeatures = result.data,
                        isTracksFeaturesLoading = false,
                        topTracksFeaturesError = null
                    )
                }
                is Resource.Error -> {
                    Log.e("getTracksFeatures", "ERROR")
                    topTracksState.value = topTracksState.value.copy(
                        topTracksFeatures = null,
                        isTracksFeaturesLoading = false,
                        topTracksFeaturesError = result.error
                    )
                }
            }
        }
    }
}


