package kt.mobile.spotify_stats.feature_artist.domain.use_case

data class ArtistUseCases(
    val getArtist: GetArtistUseCase,
    val getArtistsTopTracks: GetArtistsTopTracksUseCase,
    val getRelatedArtists: GetRelatedArtistsUseCase
)
