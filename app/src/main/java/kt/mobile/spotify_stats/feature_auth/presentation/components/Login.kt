package kt.mobile.spotify_stats.feature_auth.presentation.components

import android.app.Activity
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.ui.theme.IconLarge
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceLarge
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall
import kt.mobile.spotify_stats.core.presentation.ui.theme.authSpace
import kt.mobile.spotify_stats.feature_auth.presentation.AuthViewModel
import kt.mobile.spotify_stats.feature_auth.util.Constants

@Composable
fun Login(
    context: Context,
    authViewModel: AuthViewModel,
) {

    val ai: ApplicationInfo = context.packageManager
        .getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
    val clientId = ai.metaData["client_id"]


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            if (it.resultCode == Activity.RESULT_OK) {
                val response = AuthorizationClient.getResponse(it.resultCode, it.data)
                authViewModel.getToken(code = response.code ?: "")
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.GraphicEq,
                contentDescription = stringResource(R.string.app_icon),
                modifier = Modifier
                    .size(IconLarge),
                tint = MaterialTheme.colors.background
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
                .clip(RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp))
                .background(MaterialTheme.colors.surface),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(SpaceLarge))

            Text(
                text = stringResource(R.string.hello),
                fontSize = 32.sp,
                fontStyle = FontStyle.Normal,
                color = MaterialTheme.colors.onBackground
            )

            Spacer(modifier = Modifier.height(SpaceLarge))

            Text(
                text = stringResource(R.string.please_sign_in_to_spotify_account),
                fontSize = 22.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colors.onBackground
            )

            Spacer(modifier = Modifier.height(authSpace))

            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.Black
                ),
                onClick = {
                    Log.d("ClientId", clientId.toString())
                    val builder: AuthorizationRequest.Builder =
                        AuthorizationRequest.Builder(
                            clientId.toString(),
                            AuthorizationResponse.Type.CODE,
                            Constants.REDIRECT_URL
                        )

                    builder.setScopes(
                        arrayOf(
                            "user-top-read",
                            "user-read-currently-playing",
                            "user-read-recently-played",
                            "user-read-private"
                        )
                    )

                    val request: AuthorizationRequest = builder.build()

                    val intent =
                        AuthorizationClient.createLoginActivityIntent(context as Activity, request)
                    launcher.launch(intent)
                }
            ) {

                Text(
                    modifier = Modifier.padding(
                        start = SpaceSmall, end = SpaceSmall
                    ),
                    text = stringResource(R.string.sign_in),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.background
                )
            }
        }
    }
}