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
class GetRelatedArtistsUseCaseTest : UnitTest() {

    private lateinit var getRelatedArtistsUseCase: GetRelatedArtistsUseCase

    private lateinit var artistId: String

    @MockK
    private lateinit var artistRepository: ArtistRepository

    @Before
    fun setUp() {
        artistId = "15UsOTVnJzReFVN1VCnxy4"
        getRelatedArtistsUseCase = GetRelatedArtistsUseCase(artistRepository)
    }

    @Test
    fun `should call getRelatedArtists from repository`() = runTest {
        coEvery { artistRepository.getRelatedArtists(artistId) } returns Resource.Success(null)

        getRelatedArtistsUseCase.invoke(artistId)
        coVerify(exactly = 1) { artistRepository.getRelatedArtists(artistId) }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}