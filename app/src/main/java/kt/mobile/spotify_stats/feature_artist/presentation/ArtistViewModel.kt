package kt.mobile.spotify_stats.feature_artist.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.core.util.Constants.ARTIST_ID
import kt.mobile.spotify_stats.feature_artist.domain.use_case.ArtistUseCases
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val artistUseCases: ArtistUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _artistState = mutableStateOf(ArtistState())
    val artistState: State<ArtistState> = _artistState

    init {
        savedStateHandle.get<String>(ARTIST_ID)?.let { artistId ->
            getArtist(artistId = artistId)
            getArtistTopTracks(artistId = artistId)
            getRelatedArtists(artistId = artistId)
        }
    }

    fun getArtist(artistId: String) {
        viewModelScope.launch {

            _artistState.value = artistState.value.copy(isArtistLoading = true)

            when (val result = artistUseCases.getArtist(artistId)) {
                is Resource.Success -> {
                    _artistState.value = artistState.value.copy(
                        artist = result.data,
                        isArtistLoading = false,
                        artistError = null
                    )
                }
                is Resource.Error -> {
                    _artistState.value = artistState.value.copy(
                        artist = null,
                        isArtistLoading = false,
                        artistError = result.error
                    )

                }
            }
        }
    }

    private fun getArtistTopTracks(artistId: String) {
        viewModelScope.launch {

            _artistState.value = artistState.value.copy(isArtistsTopTracksLoading = true)

            when (val result = artistUseCases.getArtistsTopTracks(artistId)) {
                is Resource.Success -> {
                    _artistState.value = artistState.value.copy(
                        artistsTopTracks = result.data,
                        isArtistsTopTracksLoading = false,
                        artistsTopTracksError = null
                    )
                }
                is Resource.Error -> {
                    _artistState.value = artistState.value.copy(
                        isArtistsTopTracksLoading = false,
                        artistsTopTracksError = result.error
                    )

                }
            }
        }
    }

    private fun getRelatedArtists(artistId: String) {
        viewModelScope.launch {

            _artistState.value = artistState.value.copy(isRelatedArtistsLoading = true)

            when (val result = artistUseCases.getRelatedArtists(artistId)) {
                is Resource.Success -> {
                    _artistState.value = artistState.value.copy(
                        relatedArtists = result.data,
                        isRelatedArtistsLoading = false,
                        relatedArtistsError = null
                    )
                }
                is Resource.Error -> {
                    _artistState.value = artistState.value.copy(
                        isRelatedArtistsLoading = false,
                        relatedArtistsError = result.error
                    )

                }
            }
        }
    }
}