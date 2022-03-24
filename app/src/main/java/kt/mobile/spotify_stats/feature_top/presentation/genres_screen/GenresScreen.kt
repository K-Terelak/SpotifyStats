package kt.mobile.spotify_stats.feature_top.presentation.genres_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall
import kt.mobile.spotify_stats.feature_top.presentation.TermTypeEvent
import kt.mobile.spotify_stats.feature_top.presentation.TopViewModel
import kt.mobile.spotify_stats.feature_top.presentation.components.GenresArtistItem

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun GenresScreen(
    viewModel: TopViewModel,
    onNavigate: (String) -> Unit = {},
    imageLoader: ImageLoader
) {

    val state = when (viewModel.topState.value.termTypeEvent) {
        is TermTypeEvent.Short -> {
            viewModel.topState.value.short
        }
        is TermTypeEvent.Medium -> {
            viewModel.topState.value.medium
        }
        is TermTypeEvent.Long -> {
            viewModel.topState.value.long
        }
    }

    val artistsList = state.artistsState.value.topArtistsList?.toArtistsAndGenres()
    val sortedArtistsList =
        artistsList?.groupBy { it.genreTitle }?.toList()?.sortedByDescending { it.second.size }


    LazyColumn(Modifier.fillMaxWidth()) {

        sortedArtistsList?.forEach { (initial, items) ->

            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.surface)
                        .padding(SpaceSmall)
                ) {

                    Text(
                        text = initial,
                        color = MaterialTheme.colors.primary,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            items(items) { item ->
                GenresArtistItem(
                    artistImage = item.genreArtist.images.firstOrNull()?.url,
                    artistId = item.genreArtist.id,
                    artistName = item.genreArtist.name,
                    myTop = item.artistIndex+1,
                    imageLoader = imageLoader,
                    onNavigate = onNavigate
                )
            }
        }
    }
}