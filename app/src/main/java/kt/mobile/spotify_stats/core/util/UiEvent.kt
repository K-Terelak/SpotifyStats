package kt.mobile.spotify_stats.core.util

sealed class UiEvent : Event() {
    data class ShowSnackbar(val text: Int) : UiEvent()
}