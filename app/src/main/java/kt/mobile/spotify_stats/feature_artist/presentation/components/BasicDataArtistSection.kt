package kt.mobile.spotify_stats.feature_artist.presentation.components

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
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.flowlayout.FlowRow
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.data.dto.response.get_top_item.ArtistItem
import kt.mobile.spotify_stats.core.presentation.components.CenteredCircularProgress
import kt.mobile.spotify_stats.core.presentation.components.Chip
import kt.mobile.spotify_stats.core.presentation.ui.theme.*
import java.text.NumberFormat

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun BasicDataArtistSection(
    artist: ArtistItem?,
    isArtistsLoading: Boolean,
    artistsError: Int?,
) {

    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(SpaceMedium)
    ) {

        if (isArtistsLoading) {
            CenteredCircularProgress(size = ProfilePictureSizeSmall)
        } else {
            BasicDataArtistsDetailsSection(
                artist = artist,
                artistsError = artistsError,
            )
        }
    }
}

@Composable
fun BasicDataArtistsDetailsSection(
    artist: ArtistItem?,
    artistsError: Int?,
) {

    if (artist != null) {
        Column {

            Text(text = artist.name, fontSize = 28.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(SpaceLarge))

            PopularityAndFollowers(artist = artist)

            Spacer(modifier = Modifier.height(SpaceLarge))

            GenresFlowRow(artist = artist)
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(id = artistsError ?: R.string.unknown_error),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun PopularityAndFollowers(
    artist: ArtistItem
) {

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
                    append("0-10 popularity\n")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary,
                            fontSize = 20.sp
                        )
                    ) {
                        append("${(artist.popularity / 10f)}")
                    }
                },
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = buildAnnotatedString {
                    append("followers\n")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary,
                            fontSize = 20.sp
                        )
                    ) {
                        append(NumberFormat.getInstance().format(artist.followers.total))
                    }
                },
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun GenresFlowRow(
    artist: ArtistItem
) {

    Text(text = stringResource(R.string.genres), fontSize = 16.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.height(SpaceSmall))

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SpaceSmall),
        mainAxisSpacing = SpaceMedium,
        crossAxisSpacing = SpaceSmall
    ) {
        artist.genres.forEach { genre ->
            Chip(
                text = genre,
                textSize = 12.sp,
                outlineColor = MaterialTheme.colors.primary,
                onChipClick = {})
        }
    }
}