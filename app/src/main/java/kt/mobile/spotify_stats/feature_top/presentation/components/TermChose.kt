package kt.mobile.spotify_stats.feature_top.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall
import kt.mobile.spotify_stats.core.util.noRippleClickable
import kt.mobile.spotify_stats.feature_top.presentation.TermTypeEvent

@Composable
fun TermChose(
    termTypeEvent: TermTypeEvent,
    onChange: (TermTypeEvent) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SpaceSmall),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = stringResource(R.string.weeks4),
            color = if (termTypeEvent == TermTypeEvent.Short) MaterialTheme.colors.primary else Color.White,
            modifier = Modifier.noRippleClickable { onChange(TermTypeEvent.Short) }
        )

        Text(
            text = stringResource(R.string.months6),
            color = if (termTypeEvent == TermTypeEvent.Medium) MaterialTheme.colors.primary else Color.White,
            modifier = Modifier.noRippleClickable { onChange(TermTypeEvent.Medium) }
        )

        Text(
            text = stringResource(R.string.lifetime),
            color = if (termTypeEvent == TermTypeEvent.Long) MaterialTheme.colors.primary else Color.White,
            modifier = Modifier.noRippleClickable { onChange(TermTypeEvent.Long) }
        )
    }
}