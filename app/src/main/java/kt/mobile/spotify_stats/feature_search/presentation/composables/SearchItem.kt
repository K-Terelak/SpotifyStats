package kt.mobile.spotify_stats.feature_search.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.ui.theme.Shapes
import kt.mobile.spotify_stats.core.presentation.ui.theme.SongImageSizeSmall
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall
import kt.mobile.spotify_stats.core.util.noRippleClickable
import kt.mobile.spotify_stats.nav.Screen

@ExperimentalCoilApi
@Composable
fun SearchItem(
    id: String,
    image: String?,
    name: String,
    type: String,
    onNavigate: (String) -> Unit = {},
    imageLoader: ImageLoader
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SpaceSmall)
            .noRippleClickable {
                if (type == "artist") {
                    onNavigate(Screen.ArtistScreen.route + "/${id}")
                } else if (type == "track") {
                    onNavigate(Screen.TrackScreen.route + "/${id}")
                }
            },
        horizontalArrangement = Arrangement.Start,
    ) {
        Image(
            painter = rememberImagePainter(
                data = image,
                imageLoader = imageLoader
            ),
            contentDescription = stringResource(R.string.song_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(SongImageSizeSmall)
                .clip(Shapes.small)
                .align(Alignment.CenterVertically)
        )

        Column(
            modifier = Modifier
                .padding(SpaceSmall),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = name,
                fontSize = 16.sp
            )

            Text(
                text = type,
                fontSize = 10.sp
            )
        }
    }
}
