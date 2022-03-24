package kt.mobile.spotify_stats.feature_artist.presentation

import kt.mobile.spotify_stats.core.data.dto.response.get_top_item.ArtistItem
import kt.mobile.spotify_stats.feature_artist.data.remote.response.ArtistsTopTracksResponse
import kt.mobile.spotify_stats.feature_artist.data.remote.response.get_related.RelatedArtistsResponse

data class ArtistState(
    val artist: ArtistItem? = null,
    val isArtistLoading: Boolean = false,
    val artistError: String = "",
    val artistsTopTracks: ArtistsTopTracksResponse? = null,
    val isArtistsTopTracksLoading: Boolean = false,
    val artistsTopTracksError: String = "",
    val relatedArtists: RelatedArtistsResponse? = null,
    val isRelatedArtistsLoading: Boolean = false,
    val relatedArtistsError: String = ""
)
