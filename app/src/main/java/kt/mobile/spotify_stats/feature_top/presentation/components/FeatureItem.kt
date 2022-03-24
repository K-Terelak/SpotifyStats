package kt.mobile.spotify_stats.feature_top.presentation.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kt.mobile.spotify_stats.core.presentation.ui.theme.Shapes
import kt.mobile.spotify_stats.core.presentation.ui.theme.SongImageSizeLarge
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall

@Composable
fun FeatureItem(
    percent: String,
    title: String,
) {
    Card(
        modifier = Modifier
            .size(SongImageSizeLarge)
            .aspectRatio(1f)
            .padding(SpaceSmall),
        shape = Shapes.medium,
        elevation = 5.dp
    ) {

        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceSmall),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.primary,
                        fontSize = 24.sp
                    )
                ) {
                    append("$percent%\n")
                }
                withStyle(style = SpanStyle(color = Color.White, fontSize = 14.sp)) {

                    append("of your tracks are\n")

                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                        append("$title\n")
                    }
                }
            }
        )
    }
}
