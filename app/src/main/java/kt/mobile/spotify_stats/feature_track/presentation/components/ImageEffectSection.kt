package kt.mobile.spotify_stats.feature_track.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.ui.theme.IconMedium
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall

@ExperimentalCoilApi
@Composable
fun ImageEffectSection(
    image: String?,
    onNavigateUp: () -> Unit = {},
    lazyListState: LazyListState,
    imageLoader: ImageLoader
) {
    var scrolledY = 0f
    var previousOffset = 0

    Box {

        Image(
            painter = rememberImagePainter(
                data = image,
                imageLoader = imageLoader
            ),
            contentDescription = stringResource(R.string.image),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .graphicsLayer {
                    scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                    translationY = scrolledY * 0.5f
                    previousOffset = lazyListState.firstVisibleItemScrollOffset
                }
                .aspectRatio(1f)
                .fillMaxWidth()
        )

        Box(
            contentAlignment = Alignment.TopStart, modifier = Modifier
                .padding(SpaceSmall)
                .size(IconMedium)
        ) {

            IconButton(onClick = onNavigateUp) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.navigate_back)
                )
            }
        }
    }
}