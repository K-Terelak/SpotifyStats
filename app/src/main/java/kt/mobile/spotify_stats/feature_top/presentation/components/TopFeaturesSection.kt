package kt.mobile.spotify_stats.feature_top.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.components.CenteredCircularProgress
import kt.mobile.spotify_stats.core.presentation.ui.theme.ProfilePictureSizeSmall
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall
import kt.mobile.spotify_stats.feature_top.domain.models.FeatureItem
import kt.mobile.spotify_stats.feature_top.domain.models.TracksFeatures

@Composable
fun TopFeaturesSection(
    isTopFeaturesLoading: Boolean,
    tracksFeatures: TracksFeatures?,
    topFeaturesError: Int?,
) {

    Text(
        modifier = Modifier.padding(start = SpaceSmall, top = SpaceSmall),
        text = stringResource(R.string.features),
        color = Color.White
    )

    if (isTopFeaturesLoading) {
        CenteredCircularProgress(size = ProfilePictureSizeSmall)
    } else {
        TopFeaturesDetailsSection(
            features = tracksFeatures?.listFeatures,
            topFeaturesError = topFeaturesError,
        )
    }
}

@Composable
fun TopFeaturesDetailsSection(
    features: List<FeatureItem>?,
    topFeaturesError: Int?,
) {

    if (!features.isNullOrEmpty()) {

        val lazyListState = rememberLazyListState()

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            state = lazyListState,
        ) {
            items(features.sortedByDescending { it.value }) { feature ->
                FeatureItem(
                    percent = (feature.value * 100).toInt().toString(),
                    title = feature.title
                )
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(id = topFeaturesError ?: R.string.unknown_error),
                fontSize = 12.sp
            )
        }
    }

}