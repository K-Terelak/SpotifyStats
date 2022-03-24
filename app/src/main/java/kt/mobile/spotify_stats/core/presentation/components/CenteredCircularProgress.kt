package kt.mobile.spotify_stats.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import kt.mobile.spotify_stats.core.presentation.ui.theme.ProfilePictureSizeSmall

@Composable
fun CenteredCircularProgress(size: Dp = ProfilePictureSizeSmall) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(size)
        )
    }
}