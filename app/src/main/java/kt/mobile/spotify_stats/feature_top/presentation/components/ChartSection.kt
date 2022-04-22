package kt.mobile.spotify_stats.feature_top.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.domain.models.TopArtists
import kt.mobile.spotify_stats.core.presentation.components.CenteredCircularProgress
import kt.mobile.spotify_stats.core.presentation.ui.theme.ProfilePictureSizeSmall
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceMedium
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall
import kt.mobile.spotify_stats.core.util.noRippleClickable
import kt.mobile.spotify_stats.feature_top.domain.models.GenreItem
import kt.mobile.spotify_stats.nav.Screen

@ExperimentalFoundationApi
@Composable
fun ChartSection(
    isTopArtistsLoading: Boolean,
    artists: TopArtists?,
    topArtistsError: Int?,
    onNavigate: (String) -> Unit
) {

    Text(
        modifier = Modifier.padding(start = SpaceSmall, top = SpaceSmall),
        text = stringResource(R.string.chart),
        color = Color.White
    )

    if (isTopArtistsLoading) {
        CenteredCircularProgress(size = ProfilePictureSizeSmall)
    } else {
        ChartDetailsSection(
            artistsList = artists?.toArtistsAndGenres(),
            topArtistsError = topArtistsError,
            onNavigate = onNavigate
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun ChartDetailsSection(
    artistsList: List<GenreItem>?,
    topArtistsError: Int?,
    onNavigate: (String) -> Unit
) {
    if (!artistsList.isNullOrEmpty() && artistsList.size >= 6) {
        Column(modifier = Modifier.noRippleClickable { onNavigate(Screen.GenresScreen.route) }) {

            val sortedArtistsList =
                artistsList.groupBy { it.genreTitle }.toList().sortedByDescending { it.second.size }
            val max = sortedArtistsList.maxOf { it.second.size }

            for (index in 0..5) {
                var animate by remember { mutableStateOf(false) }
                val animatedValue = animateFloatAsState(
                    targetValue = if (animate) sortedArtistsList[index].second.size.toFloat() / (max.toFloat() * 1.25f) else 0f,
                    animationSpec = tween(
                        durationMillis = 1500,
                        delayMillis = 300
                    )
                )

                LaunchedEffect(key1 = true) {
                    animate = true
                }

                CustomProgressBar(
                    progress = animatedValue.value,
                    text = sortedArtistsList[index].first
                )
            }

            Text(
                text = stringResource(R.string.more),
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = SpaceSmall, bottom = SpaceMedium, top = SpaceSmall)
            )
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(id = topArtistsError ?: R.string.unknown_error),
                fontSize = 12.sp
            )
        }
    }

}