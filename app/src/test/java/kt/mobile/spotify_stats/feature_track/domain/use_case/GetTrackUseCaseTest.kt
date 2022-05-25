package kt.mobile.spotify_stats.feature_track.domain.use_case

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kt.mobile.spotify_stats.UnitTest
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_track.domain.repository.TrackRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

@ExperimentalCoroutinesApi
class GetTrackUseCaseTest : UnitTest() {

    private lateinit var getTrackUseCase: GetTrackUseCase

    private lateinit var trackId: String

    @MockK
    private lateinit var trackRepository: TrackRepository

    @Before
    fun setUp() {
        trackId = anyString()
        getTrackUseCase = GetTrackUseCase(trackRepository)
    }

    @Test
    fun `should call getTrack from repository`() = runTest {
        coEvery { trackRepository.getTrack(trackId) } returns Resource.Success(null)

        getTrackUseCase.invoke(trackId)
        coVerify(exactly = 1) { trackRepository.getTrack(trackId) }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

}