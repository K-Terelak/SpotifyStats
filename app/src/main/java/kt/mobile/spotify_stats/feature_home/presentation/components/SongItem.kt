package kt.mobile.spotify_stats.feature_home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.ui.theme.*
import kt.mobile.spotify_stats.nav.Screen

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun SongItem(
    timeAgo: String,
    songImage: String?,
    trackId: String,
    songTitle: String,
    artists: String,
    imageLoader: ImageLoader,
    onNavigate: (String) -> Unit = {},
) {

    Card(
        modifier = Modifier
            .padding(start = SpaceSmall, end = SpaceSmall, bottom = SpaceSmall)
            .fillMaxWidth(),
        shape = Shapes.small,
        elevation = 5.dp,
        onClick = {
            onNavigate(Screen.TrackScreen.route + "/${trackId}")
        }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SpaceSmall),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Replay,
                        contentDescription = stringResource(R.string.replay_icon),
                        modifier = Modifier.size(IconSmall)
                    )
                    Text(text = timeAgo, fontSize = 10.sp)
                }

                Spacer(modifier = Modifier.width(SpaceMedium))

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = songTitle,
                        fontSize = 14.sp,
                    )
                    Text(
                        text = artists,
                        fontSize = 10.sp,
                    )
                }
            }

            Image(
                painter = rememberImagePainter(
                    data = songImage,
                    imageLoader = imageLoader
                ), contentDescription = stringResource(R.string.song_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(SongImageSizeSmall)
                    .clip(Shapes.small)
            )
        }
    }
}