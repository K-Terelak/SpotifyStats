package kt.mobile.spotify_stats.feature_search.domain.use_case

data class SearchUseCases(
    val searchArtists: GetSearchArtistsUseCase,
    val searchTracks: GetSearchTracksUseCase
)