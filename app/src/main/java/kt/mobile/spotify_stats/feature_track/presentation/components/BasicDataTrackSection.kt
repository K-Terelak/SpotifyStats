package kt.mobile.spotify_stats.feature_track.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.flowlayout.FlowRow
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.components.CenteredCircularProgress
import kt.mobile.spotify_stats.core.presentation.components.Chip
import kt.mobile.spotify_stats.core.presentation.components.TopItem
import kt.mobile.spotify_stats.core.presentation.ui.theme.*
import kt.mobile.spotify_stats.core.util.ItemType
import kt.mobile.spotify_stats.feature_track.domain.models.MyTrack
import kt.mobile.spotify_stats.nav.Screen

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun BasicDataTrackSection(
    track: MyTrack?,
    isTrackLoading: Boolean,
    trackError: String,
    onNavigate: (String) -> Unit,
    imageLoader: ImageLoader
) {

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(SpaceMedium)
    ) {

        if (isTrackLoading) {
            CenteredCircularProgress(size = ProfilePictureSizeSmall)
        } else {
            BasicDataTrackDetailsSection(
                track = track,
                trackError = trackError,
                onNavigate = onNavigate,
                imageLoader = imageLoader
            )
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun BasicDataTrackDetailsSection(
    track: MyTrack?,
    trackError: String,
    onNavigate: (String) -> Unit,
    imageLoader: ImageLoader
) {

    if (track != null) {

        DurationAndPopularity(track = track)

        Spacer(modifier = Modifier.height(SpaceLarge))

        Album(
            track = track,
            onNavigate = onNavigate,
            imageLoader = imageLoader
        )

        Spacer(modifier = Modifier.height(SpaceLarge))

        ArtistFlowRow(
            track = track,
            onNavigate = onNavigate
        )
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = trackError, fontSize = 12.sp)
        }
    }
}


@Composable
fun DurationAndPopularity(
    track: MyTrack
) {

    Text(text = track.name, fontSize = 28.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.height(SpaceLarge))

    Card(
        elevation = 6.dp,
        shape = Shapes.medium,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SpaceMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = buildAnnotatedString {
                    append("duration\n")
                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                        append("${track.durationMs / 1000 / 60}:")
                        val seconds: Int = track.durationMs / 1000 % 60
                        if (seconds < 10) {
                            append("0$seconds")
                        } else {
                            append("$seconds")
                        }
                    }
                },
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = buildAnnotatedString {
                    append("0-10 popularity\n")
                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                        append("${track.popularity / 10f}")
                    }
                },
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun Album(
    track: MyTrack,
    onNavigate: (String) -> Unit,
    imageLoader: ImageLoader
) {

    Text(
        text = stringResource(R.string.album),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(SpaceSmall))

    TopItem(
        id = null,
        type = ItemType.Track,
        modifier = Modifier.size(SongImageSizeMedium),
        imageUrl = track.album.images.firstOrNull()?.url,
        text = track.album.name,
        textSize = 12.sp,
        index = null,
        imageLoader = imageLoader,
        onNavigate = onNavigate
    )
}

@Composable
fun ArtistFlowRow(
    track: MyTrack,
    onNavigate: (String) -> Unit
) {

    Text(
        text = stringResource(R.string.artist),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(SpaceSmall))

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SpaceSmall),
        mainAxisSpacing = SpaceMedium,
        crossAxisSpacing = SpaceSmall
    ) {

        track.artists.forEach { artist ->
            Chip(
                text = artist.name,
                textSize = 12.sp,
                outlineColor = MaterialTheme.colors.primary,
                onChipClick = {
                    onNavigate(Screen.ArtistScreen.route + "/${artist.id}")
                }
            )
        }
    }
}