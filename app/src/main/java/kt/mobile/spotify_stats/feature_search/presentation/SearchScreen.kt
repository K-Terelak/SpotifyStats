package kt.mobile.spotify_stats.feature_search.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.components.CenteredCircularProgress
import kt.mobile.spotify_stats.feature_search.presentation.composables.SearchItem
import kt.mobile.spotify_stats.feature_search.presentation.composables.SearchTopBar

@ExperimentalCoilApi
@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    imageLoader: ImageLoader
) {

    val items = searchViewModel.searchState.value.searchItems

    Scaffold(
        topBar = { SearchTopBar(viewModel = searchViewModel, onNavigateUp = onNavigateUp) },
        content = {

            if (searchViewModel.searchState.value.isItemsLoading) {
                CenteredCircularProgress()
            } else {
                if (items.isNullOrEmpty()) {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (searchViewModel.searchState.value.query.isNotBlank()) stringResource(
                                searchViewModel.searchState.value.itemsError
                                    ?: R.string.unknown_error
                            )
                            else "Search something!",
                            fontSize = 12.sp
                        )
                    }
                } else {
                    val lazyState = rememberLazyListState()

                    LazyColumn(state = lazyState) {

                        items(items) { item ->
                            SearchItem(
                                id = item.id,
                                image = item.imageUrl,
                                name = item.title,
                                type = item.type,
                                onNavigate = onNavigate,
                                imageLoader = imageLoader
                            )
                        }
                    }
                }
            }
        }
    )
}