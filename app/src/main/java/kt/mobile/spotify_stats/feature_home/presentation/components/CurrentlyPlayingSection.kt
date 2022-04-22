package kt.mobile.spotify_stats.feature_home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.data.dto.response.Track
import kt.mobile.spotify_stats.core.presentation.components.CenteredCircularProgress
import kt.mobile.spotify_stats.core.presentation.ui.theme.ProfilePictureSizeSmall
import kt.mobile.spotify_stats.core.presentation.ui.theme.Shapes
import kt.mobile.spotify_stats.core.presentation.ui.theme.SongImageSizeMedium
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall

@ExperimentalCoilApi
@Composable
fun CurrentlyPlayingSection(
    isCurrentlyPlayingLoading: Boolean,
    currentlyPlayingTrack: Track?,
    currentlyPlayingError: Int?,
    imageLoader: ImageLoader
) {

    Text(
        modifier = Modifier.padding(start = SpaceSmall, top = SpaceSmall),
        text = if (currentlyPlayingError != null) {
            stringResource(R.string.last_played_on_spotify)
        } else {
            stringResource(R.string.currently_playing)
        },
        color = Color.White
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(SongImageSizeMedium)
            .padding(start = SpaceSmall, end = SpaceSmall, bottom = SpaceSmall),
        shape = Shapes.medium,
    ) {

        if (isCurrentlyPlayingLoading) {
            CenteredCircularProgress(size = ProfilePictureSizeSmall)
        } else {
            CurrentlyPlayingDetailsSection(
                currentlyPlayingTrack = currentlyPlayingTrack,
                currentlyPlayingError = currentlyPlayingError,
                imageLoader = imageLoader
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CurrentlyPlayingDetailsSection(
    currentlyPlayingTrack: Track?,
    currentlyPlayingError: Int?,
    imageLoader: ImageLoader
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
    ) {

        if (currentlyPlayingTrack != null) {
            Image(
                painter = rememberImagePainter(
                    data = currentlyPlayingTrack.album.images.firstOrNull()?.url,
                    imageLoader = imageLoader
                ),
                contentDescription = stringResource(R.string.song_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(SongImageSizeMedium)
                    .clip(Shapes.small)
                    .align(CenterVertically)
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(SpaceSmall),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = currentlyPlayingTrack.name,
                    fontSize = 16.sp
                )
                var artists = ""
                currentlyPlayingTrack.artists.forEach { artistX ->
                    artists += artistX.name + ", "
                }
                Text(
                    text = artists.dropLast(2),
                    fontSize = 10.sp
                )


            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                Text(
                    text = stringResource(id = currentlyPlayingError ?: R.string.unknown_error),
                    fontSize = 12.sp
                )
            }
        }
    }
}