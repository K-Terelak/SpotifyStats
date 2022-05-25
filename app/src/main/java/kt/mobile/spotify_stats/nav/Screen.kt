package kt.mobile.spotify_stats.nav

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "home_screen")
    object Top50Screen : Screen(route = "top_screen")
    object StatsScreen : Screen(route = "stats_screen")
    object GenresScreen : Screen(route = "genres_screen")
    object SearchScreen : Screen(route = "search_screen")
    object GlobalScreen : Screen(route = "global_screen")
    object TrackScreen : Screen(route = "track_screen")
    object ArtistScreen : Screen(route = "artist_screen")
}
