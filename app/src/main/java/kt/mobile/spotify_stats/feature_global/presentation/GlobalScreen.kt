package kt.mobile.spotify_stats.feature_global.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.components.CenteredCircularProgress
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall
import kt.mobile.spotify_stats.feature_global.presentation.composables.GlobalTopBar
import kt.mobile.spotify_stats.feature_global.presentation.composables.SongItemGlobal

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun GlobalScreen(
    globalViewModel: GlobalViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit = {},
    imageLoader: ImageLoader
) {
    val globalState = globalViewModel.globalState.value

    Scaffold(
        topBar = {
            GlobalTopBar()
        },
        content = {
            if (globalState.isTracksLoading) {
                CenteredCircularProgress()
            } else {
                if (!globalState.tracks.isNullOrEmpty()) {
                    LazyColumn(modifier = Modifier.padding(top = SpaceSmall)) {
                        itemsIndexed(globalState.tracks) { index, item ->

                            var artists = ""
                            item.track.artists.forEach { artist ->
                                artists += artist.name + ", "
                            }

                            SongItemGlobal(
                                index = index + 1,
                                songImage = item.track.album.images.firstOrNull()?.url,
                                trackId = item.track.id,
                                songTitle = item.track.name,
                                artists = artists.dropLast(2),
                                imageLoader = imageLoader,
                                onNavigate = onNavigate,
                            )
                        }
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = stringResource(
                                id = globalViewModel.globalState.value.isTracksError
                                    ?: R.string.unknown_error
                            ),
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    )


}