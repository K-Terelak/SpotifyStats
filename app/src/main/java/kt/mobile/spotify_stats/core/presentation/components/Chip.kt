package kt.mobile.spotify_stats.core.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceMedium
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall
import kt.mobile.spotify_stats.core.util.noRippleClickable

@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    textSize: TextUnit = 12.sp,
    outlineColor: Color = MaterialTheme.colors.primary,
    onChipClick: () -> Unit
) {
    Text(
        text = text,
        fontSize = textSize,
        color = outlineColor,
        modifier = modifier
            .clip(RoundedCornerShape(SpaceMedium))
            .border(
                width = 1.dp,
                color = outlineColor,
                shape = RoundedCornerShape(SpaceMedium)
            )
            .noRippleClickable {
                onChipClick()
            }
            .padding(SpaceSmall)

    )
}