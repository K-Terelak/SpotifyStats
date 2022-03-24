package kt.mobile.spotify_stats.feature_artist.domain.use_case

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.core.data.dto.response.get_top_item.ArtistItem
import kt.mobile.spotify_stats.feature_artist.domain.repository.ArtistRepository

class GetArtistUseCase(
    private val repository: ArtistRepository
) {

    suspend operator fun invoke(id: String): Resource<ArtistItem> {
        return repository.getArtist(id = id)
    }
}