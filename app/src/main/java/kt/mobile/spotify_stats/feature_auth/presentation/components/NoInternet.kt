package kt.mobile.spotify_stats.feature_auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.ui.theme.IconLarge
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceLarge
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall
import kt.mobile.spotify_stats.core.presentation.ui.theme.authSpace
import kt.mobile.spotify_stats.core.util.noRippleClickable
import kt.mobile.spotify_stats.feature_auth.presentation.AuthViewModel

@Composable
fun NoInternet(
    authViewModel: AuthViewModel,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Icon(
            imageVector = Icons.Outlined.ErrorOutline,
            contentDescription = stringResource(R.string.error_icon),
            modifier = Modifier.size(IconLarge),
            tint = Color.White.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.height(SpaceLarge))

        Text(
            text = stringResource(R.string.oops_something_went_wrong),
            fontSize = 24.sp,
            color = MaterialTheme.colors.onBackground
        )

        Spacer(modifier = Modifier.height(SpaceSmall))

        Text(
            text = stringResource(R.string.check_your_internet_connection),
            fontSize = 16.sp,
            color = MaterialTheme.colors.onBackground
        )

        Spacer(modifier = Modifier.height(authSpace))

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.Black
            ),
            onClick = {
                authViewModel.renewToken()
            }
        ) {
            Text(
                modifier = Modifier.padding(
                    start = SpaceSmall,
                    end = SpaceSmall
                ),
                text = stringResource(R.string.try_again),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = MaterialTheme.colors.background
            )
        }

        Spacer(modifier = Modifier.height(SpaceSmall))

        Text(
            modifier = Modifier
                .noRippleClickable { authViewModel.logout(null) },
            text = stringResource(R.string.sign_out),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
        )


    }
}
