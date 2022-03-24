package kt.mobile.spotify_stats.feature_top.presentation

data class TopState(
    val termTypeEvent: TermTypeEvent = TermTypeEvent.Short,
    val short: ItemsState = ItemsState(),
    val medium: ItemsState = ItemsState(),
    val long: ItemsState = ItemsState(),
)


