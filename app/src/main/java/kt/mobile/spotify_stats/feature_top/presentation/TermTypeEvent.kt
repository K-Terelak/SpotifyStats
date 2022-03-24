package kt.mobile.spotify_stats.feature_top.presentation

sealed class TermTypeEvent(val term: String) {
    object Short : TermTypeEvent("short_term")
    object Medium : TermTypeEvent("medium_term")
    object Long : TermTypeEvent("long_term")
}