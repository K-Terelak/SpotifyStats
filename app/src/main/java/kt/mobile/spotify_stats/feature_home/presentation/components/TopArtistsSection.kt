package kt.mobile.spotify_stats.feature_home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.components.CenteredCircularProgress
import kt.mobile.spotify_stats.core.presentation.components.TopItem
import kt.mobile.spotify_stats.core.presentation.ui.theme.ProfilePictureSizeSmall
import kt.mobile.spotify_stats.core.presentation.ui.theme.Shapes
import kt.mobile.spotify_stats.core.presentation.ui.theme.SongImageSizeLarge
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall
import kt.mobile.spotify_stats.core.util.ItemType
import kt.mobile.spotify_stats.feature_top.domain.models.TopItem
import kt.mobile.spotify_stats.feature_top.domain.models.TopItems

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun TopArtistsSection(
    topItemType: ItemType = ItemType.Artist,
    isTopArtistsLoading: Boolean,
    topArtists: TopItems?,
    topArtistsError: String,
    onNavigate: (String) -> Unit,
    imageLoader: ImageLoader
) {

    Text(
        modifier = Modifier.padding(start = SpaceSmall, top = SpaceSmall),
        text = stringResource(R.string.top_artists_4w),
        color = Color.White
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(SongImageSizeLarge)
            .padding(start = SpaceSmall, end = SpaceSmall, bottom = SpaceSmall),
        shape = Shapes.medium,
    ) {

        if (isTopArtistsLoading) {
            CenteredCircularProgress(size = ProfilePictureSizeSmall)
        } else {
            TopArtistsDetailsSection(
                topItemType = topItemType,
                items = topArtists?.topItems,
                topArtistsError = topArtistsError,
                onNavigate = onNavigate,
                imageLoader = imageLoader
            )
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun TopArtistsDetailsSection(
    topItemType: ItemType,
    items: List<TopItem>?,
    topArtistsError: String,
    onNavigate: (String) -> Unit,
    imageLoader: ImageLoader
) {

    if (!items.isNullOrEmpty()) {
        val lazyListState = rememberLazyListState()

        LazyRow(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
        ) {

            itemsIndexed(items) { index, item ->
                TopItem(
                    id = item.id,
                    type = topItemType,
                    modifier = Modifier.aspectRatio(1f),
                    imageUrl = item.imageUrl,
                    text = item.title,
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