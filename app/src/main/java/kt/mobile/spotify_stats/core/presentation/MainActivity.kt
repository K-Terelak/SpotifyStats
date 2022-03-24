package kt.mobile.spotify_stats.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kt.mobile.spotify_stats.core.presentation.components.MyBottomNavigation
import kt.mobile.spotify_stats.core.presentation.ui.theme.Spotify_statsTheme
import kt.mobile.spotify_stats.feature_auth.presentation.AuthScreen
import kt.mobile.spotify_stats.feature_auth.presentation.AuthViewModel
import kt.mobile.spotify_stats.nav.Navigation
import javax.inject.Inject

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AuthViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * SplashScreen
         */
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.authState.value.isLoading
            }
        }

        setContent {

            Spotify_statsTheme {

                if (viewModel.authState.value.isLogged) {

                    val navController = rememberNavController()
                    val scaffoldState = rememberScaffoldState()

                    Scaffold(
                        scaffoldState = scaffoldState,
                        bottomBar = {
                            MyBottomNavigation(navController = navController)
                        },
                        content = { innerPadding ->
                            Box(modifier = Modifier.padding(innerPadding)) {
                                Navigation(
                                    scaffoldState = scaffoldState,
                                    authViewModel = viewModel,
                                    navController = navController,
                                    imageLoader = imageLoader
                                )
                            }
                        }
                    )
                } else {

                    val authScaffoldState = rememberScaffoldState()

                    Scaffold(scaffoldState = authScaffoldState) {

                        AuthScreen(
                            scaffoldState = authScaffoldState,
                            authViewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}



