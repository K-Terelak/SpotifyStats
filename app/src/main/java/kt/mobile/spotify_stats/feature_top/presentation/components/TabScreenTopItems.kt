package kt.mobile.spotify_stats.feature_top.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import kt.mobile.spotify_stats.core.presentation.components.CenteredCircularProgress
import kt.mobile.spotify_stats.core.presentation.components.TopItem
import kt.mobile.spotify_stats.core.util.ItemType
import kt.mobile.spotify_stats.feature_top.domain.models.TopItem
import kt.mobile.spotify_stats.feature_top.domain.models.TopItems

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun TabScreenTopItems(
    topItemType: ItemType,
    topItems: TopItems?,
    isLoading: Boolean,
    topItemsError: String,
    onNavigate: (String) -> Unit,
    imageLoader: ImageLoader
) {

    if (isLoading) {
        CenteredCircularProgress()
    } else {
        TabScreenDetailsSection(
            items = topItems?.topItems,
            topArtistsError = topItemsError,
            onNavigate = onNavigate,
            topItemType = topItemType,
            imageLoader = imageLoader
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun TabScreenDetailsSection(
    topItemType: ItemType,
    items: List<TopItem>?,
    topArtistsError: String,
    onNavigate: (String) -> Unit,
    imageLoader: ImageLoader
) {
    if (!items.isNullOrEmpty()) {
        val lazyListState = rememberLazyGridState()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = lazyListState
        ) {

            itemsIndexed(items) { index, item ->
                TopItem(
                    id = item.id,
                    type = topItemType,
                    modifier = Modifier.aspectRatio(1f),
                    imageUrl = item.imageUrl,
                    text = item.title,
                    index = index + 1,
                    imageLoader = imageLoader,
                    onNavigate = onNavigate
                )
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = topArtistsError, fontSize = 12.sp)
        }
    }
}