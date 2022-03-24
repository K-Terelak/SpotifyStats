package kt.mobile.spotify_stats.feature_artist.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.components.CenteredCircularProgress
import kt.mobile.spotify_stats.core.presentation.components.TopItem
import kt.mobile.spotify_stats.core.presentation.ui.theme.*
import kt.mobile.spotify_stats.core.util.ItemType
import kt.mobile.spotify_stats.feature_artist.data.remote.response.Track

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun TopTracksSection(
    isTopTracksLoading: Boolean,
    tracks: List<Track>?,
    topTracksError: String,
    onNavigate: (String) -> Unit,
    imageLoader: ImageLoader
) {

    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(SpaceMedium)
    ) {

        Column {

            Text(
                text = stringResource(R.string.top_tracks),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(SpaceSmall))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(SongImageSizeLarge),
                shape = Shapes.medium,
            ) {

                if (isTopTracksLoading) {
                    CenteredCircularProgress(size = ProfilePictureSizeSmall)
                } else {
                    TopTracksDetailsSection(
                        tracks = tracks,
                        topArtistsError = topTracksError,
                        onNavigate = onNavigate,
                        imageLoader = imageLoader
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun TopTracksDetailsSection(
    tracks: List<Track>?,
    topArtistsError: String,
    onNavigate: (String) -> Unit,
    imageLoader: ImageLoader
) {

    if (tracks != null) {
        val lazyListState = rememberLazyListState()

        LazyRow(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
        ) {
            itemsIndexed(tracks) { index, track ->
                TopItem(
                    id = track.id,
                    type = ItemType.Track,
                    modifier = Modifier.aspectRatio(1f),
                    imageUrl = track.album.images.firstOrNull()?.url,
                    text = track.name,
                    index = index + 1,
                    onNavigate = onNavigate,
                    imageLoader = imageLoader
                )
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = topArtistsError, fontSize = 12.sp)
        }
    }
}