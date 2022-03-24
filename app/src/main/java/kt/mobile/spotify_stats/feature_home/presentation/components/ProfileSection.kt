package kt.mobile.spotify_stats.feature_home.presentation.components

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.components.CenteredCircularProgress
import kt.mobile.spotify_stats.core.presentation.ui.theme.ProfilePictureSizeMedium
import kt.mobile.spotify_stats.core.presentation.ui.theme.ProfilePictureSizeSmall
import kt.mobile.spotify_stats.core.presentation.ui.theme.Shapes
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall
import kt.mobile.spotify_stats.core.util.noRippleClickable
import kt.mobile.spotify_stats.feature_home.domain.models.MyProfile

@ExperimentalCoilApi
@Composable
fun ProfileSection(
    isMyProfileLoading: Boolean,
    profile: MyProfile?,
    profileError: String,
    onLogoutClick: () -> Unit,
    imageLoader: ImageLoader
) {

    val expanded = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(ProfilePictureSizeMedium)
            .padding(SpaceSmall)
            .noRippleClickable {
                expanded.value = true
            },
        shape = Shapes.medium,
    ) {
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
        ) {
            if (profile != null) {
                DropdownMenuItem(
                    onClick = {
                        val intent = Intent(ACTION_VIEW, Uri.parse(profile.account_url))
                        context.startActivity(intent)
                    }
                ) {
                    Text("Open in spotify app")
                }

                Divider()
            }

            DropdownMenuItem(
                onClick = onLogoutClick
            ) {
                Text(
                    "Logout",
                    color = Color.Red
                )
            }
        }

        if (isMyProfileLoading) {
            CenteredCircularProgress(size = ProfilePictureSizeSmall)
        } else {
            ProfileDetailsSection(
                profile = profile,
                proleError = profileError,
                imageLoader = imageLoader
            )
        }
    }
}


@ExperimentalCoilApi
@Composable
fun ProfileDetailsSection(
    profile: MyProfile?,
    proleError: String,
    imageLoader: ImageLoader
) {

    if (profile != null) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SpaceSmall),
            horizontalArrangement = SpaceBetween,
            verticalAlignment = CenterVertically
        ) {

            Text(
                text = "Hello ${profile.displayName}!",
                fontSize = 18.sp,
                color = Color.White
            )

            Image(
                painter = rememberImagePainter(
                    data = profile.image,
                    imageLoader = imageLoader
                ), contentDescription = stringResource(R.string.profile_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(ProfilePictureSizeSmall)
                    .clip(CircleShape)
            )
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
            Text(text = proleError, fontSize = 12.sp)
        }
    }
}

