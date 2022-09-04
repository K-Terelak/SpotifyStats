package kt.mobile.spotify_stats.feature_top.domain.use_case

data class TopUseCases(
    val getTopArtists: GetTopArtistsUseCase,
    val getTopItems: GetTopItemsUseCase,
    val getTracksFeatures: GetTracksFeaturesUseCase
)
