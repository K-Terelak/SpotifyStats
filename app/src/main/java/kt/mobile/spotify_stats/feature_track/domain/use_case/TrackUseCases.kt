package kt.mobile.spotify_stats.feature_track.domain.use_case

data class TrackUseCases(
    val getTrack: GetTrackUseCase,
    val getTrackFeatures: GetTrackFeaturesUseCase
)
