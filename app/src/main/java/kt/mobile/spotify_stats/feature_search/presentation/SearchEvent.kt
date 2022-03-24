package kt.mobile.spotify_stats.feature_search.presentation

sealed class SearchEvent {
    data class Query(val query: String) : SearchEvent()
}
