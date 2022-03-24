package kt.mobile.spotify_stats.feature_top.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceLarge
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceMedium
import kt.mobile.spotify_stats.core.util.noRippleClickable
import kt.mobile.spotify_stats.nav.Screen

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun GenresArtistItem(
    artistImage: String?,
    artistId: String,
    artistName: String,
    myTop: Int,
    imageLoader: ImageLoader,
    onNavigate: (String) -> Unit = {},
) {

    Row(
        modifier = Modifier
            .padding(start = SpaceMedium, end = SpaceMedium, bottom = SpaceLarge)
            .fillMaxWidth()
            .noRippleClickable {
                onNavigate(Screen.ArtistScreen.route + "/${artistId}")
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = rememberImagePainter(
                    data = artistImage,
                    imageLoader = imageLoader
                ),
                contentDescription = stringResource(R.string.artist_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(SongImageSizeSmall)
                    .clip(Shapes.small)
            )

            Spacer(modifier = Modifier.width(SpaceMedium))

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = artistName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "#$myTop top artist",
                    fontSize = 14.sp,
                )

            }
        }

        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            tint = Color.White.copy(0.6f),
            contentDescription = stringResource(R.string.navigation_icon)
        )
    }
}