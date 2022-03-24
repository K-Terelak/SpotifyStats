package kt.mobile.spotify_stats.feature_search.presentation

import kt.mobile.spotify_stats.feature_search.domain.models.MySearchItem

data class SearchState(
    val query: String = "",
    val searchItems: List<MySearchItem>? = emptyList(),
    val isItemsLoading: Boolean = false,
    val itemsError: String = ""
)
