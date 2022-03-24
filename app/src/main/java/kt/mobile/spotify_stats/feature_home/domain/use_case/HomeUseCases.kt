package kt.mobile.spotify_stats.feature_home.domain.use_case

data class HomeUseCases(
    val getMyProfile: GetMyProfileUseCase,
    val getCurrentlyPlaying: GetCurrentlyPlayingUseCase,
    val getRecentlyPlayed: GetRecentlyPlayedUseCase
)
