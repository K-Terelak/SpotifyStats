package kt.mobile.spotify_stats.feature_track.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.components.CenteredCircularProgress
import kt.mobile.spotify_stats.core.presentation.ui.theme.ProfilePictureSizeSmall
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceMedium
import kt.mobile.spotify_stats.feature_top.domain.models.FeatureItem

@Composable
fun TrackFeaturesSection(
    trackFeature: List<FeatureItem>?,
    isTrackFeaturesLoading: Boolean,
    trackFeaturesError: String,
) {

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(SpaceMedium)
    ) {

        if (isTrackFeaturesLoading) {
            CenteredCircularProgress(size = ProfilePictureSizeSmall)
        } else {
            TrackFeaturesDetailsSection(
                trackFeature = trackFeature,
                trackFeaturesError = trackFeaturesError
            )
        }
    }
}

@Composable
fun TrackFeaturesDetailsSection(
    trackFeature: List<FeatureItem>?,
    trackFeaturesError: String,
) {

    if (trackFeature != null) {

        Text(
            text = stringResource(R.string.features),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ChartFeatures(myAudioFeature = trackFeature)
        }
    } else {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = trackFeaturesError, fontSize = 12.sp)
        }
    }
}