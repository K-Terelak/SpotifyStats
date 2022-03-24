package kt.mobile.spotify_stats.feature_top.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class ItemsState(
    val artistsState: MutableState<TopArtistsState> = mutableStateOf(TopArtistsState()),
    val tracksState: MutableState<TopTracksState> = mutableStateOf(TopTracksState()),
)