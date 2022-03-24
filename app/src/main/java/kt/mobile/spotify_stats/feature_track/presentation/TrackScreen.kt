package kt.mobile.spotify_stats.feature_track.presentation

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
import kt.mobile.spotify_stats.feature_track.presentation.components.BasicDataTrackSection
import kt.mobile.spotify_stats.feature_track.presentation.components.ImageEffectSection
import kt.mobile.spotify_stats.feature_track.presentation.components.TrackFeaturesSection

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun TrackScreen(
    viewModel: TrackViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit = {},
    onNavigate: (String) -> Unit,
    imageLoader: ImageLoader
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
                image = viewModel.trackState.value.track?.album?.images?.firstOrNull()?.url,
                lazyListState = lazyListState,
                onNavigateUp = onNavigateUp,
                imageLoader = imageLoader
            )
        }

        item {
            BasicDataTrackSection(
                track = viewModel.trackState.value.track,
                isTrackLoading = viewModel.trackState.value.isTrackLoading,
                trackError = viewModel.trackState.value.trackError,
                onNavigate = onNavigate,
                imageLoader = imageLoader
            )
        }

        item {
            TrackFeaturesSection(
                trackFeature = viewModel.trackState.value.trackFeatures?.listFeatures,
                isTrackFeaturesLoading = viewModel.trackState.value.isTrackFeaturesLoading,
                trackFeaturesError = viewModel.trackState.value.trackFeaturesError,
            )
        }
    }
}





