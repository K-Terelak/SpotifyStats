package kt.mobile.spotify_stats.core.util

sealed class ItemType {
    object Artist : ItemType()
    object Track : ItemType()
}
