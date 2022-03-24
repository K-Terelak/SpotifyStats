package kt.mobile.spotify_stats.feature_artist.domain.use_case

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_artist.data.remote.response.get_related.RelatedArtistsResponse
import kt.mobile.spotify_stats.feature_artist.domain.repository.ArtistRepository

class GetRelatedArtistsUseCase(
    private val repository: ArtistRepository
) {

    suspend operator fun invoke(id: String): Resource<RelatedArtistsResponse> {
        return repository.getRelatedArtists(id = id)
    }
}