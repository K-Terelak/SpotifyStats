package kt.mobile.spotify_stats.feature_top.presentation

import kt.mobile.spotify_stats.core.domain.models.TopArtists

data class TopArtistsState(
    val isTopArtistsLoading: Boolean = false,
    val topArtistsList: TopArtists? = null,
    val topArtistsError: Int? = null,
)
