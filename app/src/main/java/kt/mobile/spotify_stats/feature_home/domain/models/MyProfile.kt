package kt.mobile.spotify_stats.feature_home.domain.models

data class MyProfile(
    val country: String,
    val displayName: String,
    val account_url: String,
    val followersCount: Int,
    val image: String? = "",
)
