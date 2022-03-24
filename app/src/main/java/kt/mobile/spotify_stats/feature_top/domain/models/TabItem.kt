package kt.mobile.spotify_stats.feature_top.domain.models

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(
    var title: String,
    var screen: ComposableFun,
) {

    data class MyTab(
        val myTitle: String,
        val myScreen: ComposableFun
    ) : TabItem(myTitle, myScreen)
}


