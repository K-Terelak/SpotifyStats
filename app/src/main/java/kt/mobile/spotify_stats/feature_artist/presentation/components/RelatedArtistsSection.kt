package kt.mobile.spotify_stats.feature_artist.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.components.CenteredCircularProgress
import kt.mobile.spotify_stats.core.presentation.components.TopItem
import kt.mobile.spotify_stats.core.presentation.ui.theme.*
import kt.mobile.spotify_stats.core.util.ItemType
import kt.mobile.spotify_stats.feature_artist.data.remote.response.get_related.Artist

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun RelatedArtistsSection(
    relatedArtists: List<Artist>?,
    isRelatedArtistsLoading: Boolean,
    relatedArtistsError: Int?,
    onNavigate: (String) -> Unit,
    imageLoader: ImageLoader
) {

    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(SpaceMedium)
    ) {

        Column {

            Text(text = "Related artists", fontSize = 16.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(SpaceSmall))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(SongImageSizeMedium)
                    .padding(start = SpaceSmall, end = SpaceSmall, bottom = SpaceSmall),
                shape = Shapes.medium,
            ) {
                if (isRelatedArtistsLoading) {
                    CenteredCircularProgress(size = ProfilePictureSizeSmall)
                } else {
                    RelatedArtistsDetailsSection(
                        relatedArtists = relatedArtists,
                        relatedArtistsError = relatedArtistsError,
                        onNavigate = onNavigate,
                        imageLoader = imageLoader
                    )
                }
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun RelatedArtistsDetailsSection(
    relatedArtists: List<Artist>?,
    relatedArtistsError: Int?,
    onNavigate: (String) -> Unit,
    imageLoader: ImageLoader
) {

    if (!relatedArtists.isNullOrEmpty()) {
        val lazyListState = rememberLazyListState()

        LazyRow(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
        ) {
            items(relatedArtists) { artist ->
                TopItem(
                    id = artist.id,
                    type = ItemType.Artist,
                    textSize = 12.sp,
                    modifier = Modifier.aspectRatio(1f),
                    imageUrl = artist.images.firstOrNull()?.url,
                    text = artist.name,
                    index = null,
                    onNavigate = onNavigate,
                    imageLoader = imageLoader
                )
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = stringResource(id = relatedArtistsError?: R.string.unknown_error), fontSize = 12.sp)
        }
    }
}