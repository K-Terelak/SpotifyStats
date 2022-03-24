package kt.mobile.spotify_stats.feature_global.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.ui.theme.Shapes
import kt.mobile.spotify_stats.core.presentation.ui.theme.SongImageSizeSmall
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceMedium
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall
import kt.mobile.spotify_stats.core.util.noRippleClickable
import kt.mobile.spotify_stats.nav.Screen

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun SongItemGlobal(
    index: Int,
    songImage: String?,
    trackId: String,
    songTitle: String,
    artists: String,
    imageLoader: ImageLoader,
    onNavigate: (String) -> Unit = {},
) {

    Row(
        modifier = Modifier
            .padding(start = SpaceSmall, end = SpaceSmall, bottom = SpaceMedium)
            .fillMaxWidth()
            .noRippleClickable {
                onNavigate(Screen.TrackScreen.route + "/${trackId}")
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "#$index",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(SpaceMedium)
        )

        Image(
            painter = rememberImagePainter(
                data = songImage,
                imageLoader = imageLoader
            ),
            contentDescription = stringResource(R.string.song_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(SongImageSizeSmall)
                .clip(Shapes.small)
        )

        Spacer(modifier = Modifier.width(SpaceSmall))

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = songTitle,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = artists,
                fontSize = 10.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}