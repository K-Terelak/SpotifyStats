package kt.mobile.spotify_stats.feature_artist.domain.use_case

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_artist.data.remote.response.ArtistsTopTracksResponse
import kt.mobile.spotify_stats.feature_artist.domain.repository.ArtistRepository

class GetArtistsTopTracksUseCase(
    private val repository: ArtistRepository
) {

    suspend operator fun invoke(id: String): Resource<ArtistsTopTracksResponse> {
        return repository.getArtistsTopTracks(id = id)
    }
}
