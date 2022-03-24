package kt.mobile.spotify_stats.feature_top.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kt.mobile.spotify_stats.core.presentation.ui.theme.Shapes
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall

@Composable
fun CustomProgressBar(
    progress: Float = 0f,
    height: Dp = 26.dp,
    text: String
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SpaceSmall)
    ) {

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .clip(Shapes.medium),
            backgroundColor = MaterialTheme.colors.surface,
            color = MaterialTheme.colors.primary
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = SpaceSmall),
            text = text,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}