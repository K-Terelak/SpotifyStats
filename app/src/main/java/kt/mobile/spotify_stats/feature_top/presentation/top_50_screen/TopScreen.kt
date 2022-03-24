package kt.mobile.spotify_stats.feature_top.presentation.top_50_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.util.ItemType
import kt.mobile.spotify_stats.feature_top.domain.models.TabItem
import kt.mobile.spotify_stats.feature_top.presentation.TermTypeEvent
import kt.mobile.spotify_stats.feature_top.presentation.TopViewModel
import kt.mobile.spotify_stats.feature_top.presentation.components.TabScreenTopItems
import kt.mobile.spotify_stats.feature_top.presentation.components.Tabs
import kt.mobile.spotify_stats.feature_top.presentation.components.TabsContent
import kt.mobile.spotify_stats.feature_top.presentation.components.TermChose

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun TopScreen(
    viewModel: TopViewModel,
    onNavigate: (String) -> Unit,
    imageLoader: ImageLoader
) {
    val artistsState = when (viewModel.topState.value.termTypeEvent) {
        is TermTypeEvent.Short -> {
            viewModel.topState.value.short.artistsState.value
        }
        is TermTypeEvent.Medium -> {
            viewModel.topState.value.medium.artistsState.value
        }
        is TermTypeEvent.Long -> {
            viewModel.topState.value.long.artistsState.value
        }
    }

    val trackState = when (viewModel.topState.value.termTypeEvent) {
        is TermTypeEvent.Short -> {
            viewModel.topState.value.short.tracksState.value
        }
        is TermTypeEvent.Medium -> {
            viewModel.topState.value.medium.tracksState.value
        }
        is TermTypeEvent.Long -> {
            viewModel.topState.value.long.tracksState.value
        }
    }

    val tabs = listOf(
        TabItem.MyTab(
            myTitle = stringResource(R.string.artists),
            myScreen = {
                TabScreenTopItems(
                    topItems = artistsState.topArtistsList?.toTopItems(),
                    topItemsError = artistsState.topArtistsError,
                    isLoading = artistsState.isTopArtistsLoading,
                    onNavigate = onNavigate,
                    topItemType = ItemType.Artist,
                    imageLoader = imageLoader
                )
            }
        ),
        TabItem.MyTab(
            myTitle = stringResource(R.string.tracks),
            myScreen = {
                TabScreenTopItems(
                    topItems = trackState.topTracksList?.toTopItems(),
                    topItemsError = trackState.topTracksError,
                    isLoading = trackState.isTopTracksLoading,
                    onNavigate = onNavigate,
                    topItemType = ItemType.Track,
                    imageLoader = imageLoader
                )
            }
        )
    )

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Tabs(tabs = tabs, pagerState = pagerState)

        TermChose(
            termTypeEvent = viewModel.topState.value.termTypeEvent,
            onChange = { type ->
                viewModel.onEvent(type)
            })

        TabsContent(tabs = tabs, pagerState = pagerState)
    }
}


