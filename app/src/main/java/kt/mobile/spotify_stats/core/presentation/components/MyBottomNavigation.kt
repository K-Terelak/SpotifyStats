package kt.mobile.spotify_stats.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kt.mobile.spotify_stats.core.domain.models.BottomNavItem
import kt.mobile.spotify_stats.core.presentation.ui.theme.IconSmall


@Composable
fun MyBottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Top,
        BottomNavItem.Stats,
        BottomNavItem.Search,
        BottomNavItem.Global
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            modifier = Modifier.size(IconSmall)
                        )
                    }
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = false,
                label = {
                    Text(
                        text = item.title,
                        fontSize = 8.sp,
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = false
                    }
                }
            )
        }
    }
}