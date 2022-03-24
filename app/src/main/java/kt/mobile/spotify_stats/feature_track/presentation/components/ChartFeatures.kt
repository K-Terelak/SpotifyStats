package kt.mobile.spotify_stats.feature_track.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall
import kt.mobile.spotify_stats.feature_top.domain.models.FeatureItem
import kt.mobile.spotify_stats.feature_top.presentation.components.CustomProgressBar

@Composable
fun ChartFeatures(
    myAudioFeature: List<FeatureItem>
) {

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(SpaceSmall)
    ) {

        myAudioFeature.forEach { feature ->

            Spacer(modifier = Modifier.height(SpaceSmall))

            Text(
                text = feature.title,
                fontSize = 12.sp
            )

            CustomProgressBar(
                text = "",
                progress = (feature.value),
                height = 6.dp
            )

        }
    }
}