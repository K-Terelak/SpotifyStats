package kt.mobile.spotify_stats.feature_artist.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import kt.mobile.spotify_stats.feature_artist.presentation.components.BasicDataArtistSection
import kt.mobile.spotify_stats.feature_artist.presentation.components.RelatedArtistsSection
import kt.mobile.spotify_stats.feature_artist.presentation.components.TopTracksSection
import kt.mobile.spotify_stats.feature_track.presentation.components.ImageEffectSection

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun ArtistScreen(
    artistViewModel: ArtistViewModel = hiltViewModel(),
    imageLoader: ImageLoader,
    onNavigateUp: () -> Unit = { },
    onNavigate: (String) -> Unit,
) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize(),
        state = lazyListState,
    ) {


        item {
            ImageEffectSection(
                image = artistViewModel.artistState.value.artist?.images?.firstOrNull()?.url,
                lazyListState = lazyListState,
                onNavigateUp = onNavigateUp,
                imageLoader = imageLoader
            )
        }

        item {
            BasicDataArtistSection(
                artist = artistViewModel.artistState.value.artist,
                isArtistsLoading = artistViewModel.artistState.value.isArtistLoading,
                artistsError = artistViewModel.artistState.value.artistError,
            )
        }
        item {
            TopTracksSection(
                tracks = artistViewModel.artistState.value.artistsTopTracks?.tracks,
                isTopTracksLoading = artistViewModel.artistState.value.isArtistsTopTracksLoading,
                topTracksError = artistViewModel.artistState.value.artistsTopTracksError,
                onNavigate = onNavigate,
                imageLoader = imageLoader
            )
        }

        item {
            RelatedArtistsSection(
                relatedArtists = artistViewModel.artistState.value.relatedArtists?.artists,
                isRelatedArtistsLoading = artistViewModel.artistState.value.isRelatedArtistsLoading,
                relatedArtistsError = artistViewModel.artistState.value.relatedArtistsError,
                onNavigate = onNavigate,
                imageLoader = imageLoader
            )
        }
    }
}
