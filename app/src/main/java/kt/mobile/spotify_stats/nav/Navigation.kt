package kt.mobile.spotify_stats.nav

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kt.mobile.spotify_stats.core.util.Constants.ARTIST_ID
import kt.mobile.spotify_stats.core.util.Constants.TRACK_ID
import kt.mobile.spotify_stats.feature_artist.presentation.ArtistScreen
import kt.mobile.spotify_stats.feature_auth.presentation.AuthViewModel
import kt.mobile.spotify_stats.feature_global.presentation.GlobalScreen
import kt.mobile.spotify_stats.feature_home.presentation.HomeScreen
import kt.mobile.spotify_stats.feature_search.presentation.SearchScreen
import kt.mobile.spotify_stats.feature_top.presentation.TopViewModel
import kt.mobile.spotify_stats.feature_top.presentation.genres_screen.GenresScreen
import kt.mobile.spotify_stats.feature_top.presentation.top_50_screen.TopScreen
import kt.mobile.spotify_stats.feature_top.presentation.top_stats_screen.StatsScreen
import kt.mobile.spotify_stats.feature_track.presentation.TrackScreen


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun Navigation(
    scaffoldState: ScaffoldState,
    authViewModel: AuthViewModel,
    navController: NavHostController,
    imageLoader: ImageLoader
) {

    val sharedTopViewModel: TopViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {

        composable(
            route = Screen.HomeScreen.route,
        ) {
            HomeScreen(
                authViewModel = authViewModel,
                topViewModel = sharedTopViewModel,
                onNavigate = navController::navigate,
                scaffoldState = scaffoldState,
                imageLoader = imageLoader
            )
        }

        composable(
            route = Screen.Top50Screen.route,
        ) {
            TopScreen(
                viewModel = sharedTopViewModel,
                onNavigate = navController::navigate,
                imageLoader = imageLoader
            )
        }

        composable(
            route = Screen.StatsScreen.route,
        ) {
            StatsScreen(
                viewModel = sharedTopViewModel,
                onNavigate = navController::navigate
            )
        }

        composable(
            route = Screen.GenresScreen.route,
        ) {
            GenresScreen(
                viewModel = sharedTopViewModel,
                onNavigate = navController::navigate,
                imageLoader = imageLoader
            )
        }

        composable(
            route = Screen.SearchScreen.route,
        ) {
            SearchScreen(
                onNavigate = navController::navigate,
                onNavigateUp = navController::navigateUp,
                imageLoader = imageLoader
            )
        }

        composable(
            route = Screen.GlobalScreen.route,
        ) {
            GlobalScreen(
                onNavigate = navController::navigate,
                imageLoader = imageLoader
            )
        }

        composable(
            route = Screen.TrackScreen.route + "/{$TRACK_ID}",
            arguments = listOf(
                navArgument(name = TRACK_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            TrackScreen(
                imageLoader = imageLoader,
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate
            )
        }

        composable(
            route = Screen.ArtistScreen.route + "/{$ARTIST_ID}",
            arguments = listOf(
                navArgument(name = ARTIST_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            ArtistScreen(
                imageLoader = imageLoader,
                onNavigate = navController::navigate,
                onNavigateUp = navController::navigateUp,
            )
        }
    }
}