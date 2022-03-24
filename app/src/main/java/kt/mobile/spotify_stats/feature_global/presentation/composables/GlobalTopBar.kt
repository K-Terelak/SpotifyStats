package kt.mobile.spotify_stats.feature_global.presentation.composables

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kt.mobile.spotify_stats.R

@Composable
fun GlobalTopBar() {

    TopAppBar(
        title = {
            Text(text = stringResource(R.string.top_50_world))
        },
    )
}