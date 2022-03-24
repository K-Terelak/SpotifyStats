package kt.mobile.spotify_stats.core.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.ui.theme.Shapes
import kt.mobile.spotify_stats.core.presentation.ui.theme.SongImageSizeLarge
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall
import kt.mobile.spotify_stats.core.util.ItemType
import kt.mobile.spotify_stats.nav.Screen

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun TopItem(
    id: String?,
    type: ItemType,
    modifier: Modifier,
    imageUrl: String?,
    text: String,
    textSize: TextUnit = 16.sp,
    indexTextSize: TextUnit = 24.sp,
    index: Int?,
    onNavigate: (String) -> Unit,
    imageLoader: ImageLoader,
) {
    Card(
        modifier = modifier
            .size(SongImageSizeLarge)
            .padding(SpaceSmall),
        shape = Shapes.medium,
        elevation = 5.dp,
        onClick = {
            if (!id.isNullOrEmpty()) {
                when (type) {
                    is ItemType.Track -> {
                        Log.d("track clicked", id)
                        onNavigate(Screen.TrackScreen.route + "/${id}")
                    }
                    is ItemType.Artist -> {
                        Log.d("artist clicked", id)
                        onNavigate(Screen.ArtistScreen.route + "/${id}")
                    }
                }
            }
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = rememberImagePainter(
                    data = imageUrl,
                    imageLoader = imageLoader
                ),
                contentDescription = stringResource(R.string.song_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceSmall),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(4f, 4f),
                        blurRadius = 8f
                    )
                ),
                text = if (index != null) "#$index" else "",
                fontWeight = FontWeight.Bold,
                fontSize = indexTextSize,
                color = Color.White,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceSmall),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(4f, 4f),
                        blurRadius = 8f
                    )
                ),
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = textSize,
                color = Color.White
            )
        }
    }
}