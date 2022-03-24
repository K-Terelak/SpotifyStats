package kt.mobile.spotify_stats.feature_home.domain.use_case

import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_home.domain.models.MyProfile
import kt.mobile.spotify_stats.feature_home.domain.repository.HomeRepository

class GetMyProfileUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Resource<MyProfile> {
        return repository.getMyProfile()
    }
}