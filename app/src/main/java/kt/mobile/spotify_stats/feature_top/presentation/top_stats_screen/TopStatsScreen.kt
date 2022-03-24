package kt.mobile.spotify_stats.feature_top.presentation.top_stats_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import kt.mobile.spotify_stats.feature_top.presentation.TermTypeEvent
import kt.mobile.spotify_stats.feature_top.presentation.TopViewModel
import kt.mobile.spotify_stats.feature_top.presentation.components.ChartSection
import kt.mobile.spotify_stats.feature_top.presentation.components.TermChose
import kt.mobile.spotify_stats.feature_top.presentation.components.TopFeaturesSection

@ExperimentalFoundationApi
@Composable
fun StatsScreen(
    viewModel: TopViewModel,
    onNavigate: (String) -> Unit
) {

    val state = when (viewModel.topState.value.termTypeEvent) {
        is TermTypeEvent.Short -> {
            viewModel.topState.value.short
        }
        is TermTypeEvent.Medium -> {
            viewModel.topState.value.medium
        }
        is TermTypeEvent.Long -> {
            viewModel.topState.value.long
        }
    }


    Column {
        TermChose(
            termTypeEvent = viewModel.topState.value.termTypeEvent,
            onChange = { type ->
                viewModel.onEvent(type)
            }
        )

        ChartSection(
            isTopArtistsLoading = state.artistsState.value.isTopArtistsLoading,
            artists = state.artistsState.value.topArtistsList,
            topArtistsError = state.artistsState.value.topArtistsError,
            onNavigate = onNavigate
        )

        TopFeaturesSection(
            isTopFeaturesLoading = state.tracksState.value.isTracksFeaturesLoading,
            tracksFeatures = state.tracksState.value.topTracksFeatures,
            topFeaturesError = state.tracksState.value.topTracksFeaturesError
        )
    }
}
