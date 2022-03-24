package kt.mobile.spotify_stats.feature_home.presentation

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.collectLatest
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.core.presentation.components.CenteredCircularProgress
import kt.mobile.spotify_stats.core.presentation.ui.theme.ProfilePictureSizeSmall
import kt.mobile.spotify_stats.core.presentation.ui.theme.SpaceSmall
import kt.mobile.spotify_stats.core.util.DateFormatUtil
import kt.mobile.spotify_stats.core.util.ItemType
import kt.mobile.spotify_stats.core.util.UiEvent
import kt.mobile.spotify_stats.feature_auth.presentation.AuthViewModel
import kt.mobile.spotify_stats.feature_home.presentation.components.CurrentlyPlayingSection
import kt.mobile.spotify_stats.feature_home.presentation.components.ProfileSection
import kt.mobile.spotify_stats.feature_home.presentation.components.SongItem
import kt.mobile.spotify_stats.feature_home.presentation.components.TopArtistsSection
import kt.mobile.spotify_stats.feature_top.presentation.TopViewModel

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    authViewModel: AuthViewModel,
    topViewModel: TopViewModel,
    onNavigate: (String) -> Unit,
    scaffoldState: ScaffoldState,
    imageLoader: ImageLoader
) {

    val context = LocalContext.current

    val homeState = homeViewModel.homeState.value
    val topState = topViewModel.topState.value

    val isRefreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    val lazyListState = rememberLazyListState()

    LaunchedEffect(key1 = true) {
        homeViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText
                    )
                }
            }
        }
    }

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            homeViewModel.loadData()
            topViewModel.loadData()
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceSmall),
            state = lazyListState
        ) {

            item {
                ProfileSection(
                    isMyProfileLoading = homeState.isMyProfileLoading,
                    profile = homeState.myProfile,
                    profileError = homeState.profileError,
                    onLogoutClick = {
                        authViewModel.logout(context as Activity)
                    },
                    imageLoader = imageLoader
                )
            }

            item {
                TopArtistsSection(
                    isTopArtistsLoading = topState.short.artistsState.value.isTopArtistsLoading,
                    topArtists = topState.short.artistsState.value.topArtistsList?.toTopItems(),
                    topArtistsError = topState.short.artistsState.value.topArtistsError,
                    onNavigate = onNavigate,
                    topItemType = ItemType.Artist,
                    imageLoader = imageLoader
                )
            }

            item {
                CurrentlyPlayingSection(
                    isCurrentlyPlayingLoading = homeState.isCurrentlyPlayingLoading,
                    currentlyPlayingTrack = if (homeState.currentlyPlaying != null) {
                        homeState.currentlyPlaying.track
                    } else {
                        homeState.recentlyPlayed?.listSongs?.firstOrNull()?.track
                    },
                    currentlyPlayingError = homeState.currentlyPlayingError,
                    imageLoader = imageLoader
                )
            }

            item {
                Text(
                    modifier = Modifier.padding(start = SpaceSmall, top = SpaceSmall),
                    text = stringResource(R.string.recently_played),
                    color = Color.White
                )
            }

            if (homeState.isRecentlyPlayedLoading) {
                item {
                    CenteredCircularProgress(size = ProfilePictureSizeSmall)
                }
            } else {
                if (homeState.recentlyPlayed != null) {
                    items(homeState.recentlyPlayed.listSongs) { song ->
                        var artists = ""
                        song.track.artists.forEach { artistX ->
                            artists += artistX.name + ", "
                        }
                        SongItem(
                            timeAgo = DateFormatUtil.calculateTimeDIff(song.playedAt),
                            songImage = song.track.album.images.firstOrNull()?.url,
                            trackId = song.track.id,
                            songTitle = song.track.name,
                            artists = artists.dropLast(2),
                            imageLoader = imageLoader,
                            onNavigate = onNavigate,
                        )
                    }
                } else {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = homeState.recentlyPlayedError,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(SpaceSmall)
                            )
                        }
                    }
                }
            }
        }
    }
}
