package kt.mobile.spotify_stats.feature_track.domain.repository

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kt.mobile.spotify_stats.UnitTest
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_track.domain.use_case.GetTrackFeaturesUseCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

@ExperimentalCoroutinesApi
class GetTrackFeaturesUseCaseTest : UnitTest() {

    private lateinit var getTrackFeaturesUseCase: GetTrackFeaturesUseCase

    private lateinit var trackId: String

    @MockK
    private lateinit var trackRepository: TrackRepository

    @Before
    fun setUp() {
        trackId = anyString()
        getTrackFeaturesUseCase = GetTrackFeaturesUseCase(trackRepository)
    }

    @Test
    fun `should call getTrack from repository`() = runTest {
        coEvery { trackRepository.getTrackFeatures(trackId) } returns Resource.Success(null)

        getTrackFeaturesUseCase.invoke(trackId)
        coVerify(exactly = 1) { trackRepository.getTrackFeatures(trackId) }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}