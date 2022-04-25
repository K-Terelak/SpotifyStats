package kt.mobile.spotify_stats.feature_artist.domain.use_case

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kt.mobile.spotify_stats.UnitTest
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_artist.domain.repository.ArtistRepository
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetArtistUseCaseTest : UnitTest() {

    private lateinit var getArtistUseCase: GetArtistUseCase

    private lateinit var artistId: String

    @MockK
    private lateinit var artistRepository: ArtistRepository

    @Before
    fun setUp() {
        artistId = "15UsOTVnJzReFVN1VCnxy4"
        getArtistUseCase = GetArtistUseCase(artistRepository)
    }

    @Test
    fun `should call getArtist from repository`() = runTest {
        coEvery { artistRepository.getArtist(artistId) } returns Resource.Success(null)

        getArtistUseCase.invoke(artistId)
        coVerify(exactly = 1) { artistRepository.getArtist(artistId) }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}