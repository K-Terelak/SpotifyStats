package kt.mobile.spotify_stats.core.domain.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import kt.mobile.spotify_stats.nav.Screen

sealed class BottomNavItem(var title: String, var icon: ImageVector, var route: String) {

    object Home : BottomNavItem("Home", Icons.Default.Home, Screen.HomeScreen.route)
    object Top : BottomNavItem("Top", Icons.Default.TrendingUp, Screen.Top50Screen.route)
    object Stats : BottomNavItem("Stats", Icons.Default.BarChart, Screen.StatsScreen.route)
    object Search : BottomNavItem("Search", Icons.Default.Search, Screen.SearchScreen.route)
    object Global : BottomNavItem("Global Chart", Icons.Default.Language, Screen.GlobalScreen.route)
}