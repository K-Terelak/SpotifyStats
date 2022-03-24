package kt.mobile.spotify_stats.feature_top.domain.models

import kt.mobile.spotify_stats.core.data.dto.response.Track


data class TopTracks(
    val tracks: List<Track> = emptyList(),
) {

    fun toTopItems(): TopItems {
        return TopItems(
            topItems = tracks.map { track ->
                TopItem(
                    id = track.id,
                    title = track.name,
                    imageUrl = track.album.images.firstOrNull()?.url
                )
            }
        )
    }
}
