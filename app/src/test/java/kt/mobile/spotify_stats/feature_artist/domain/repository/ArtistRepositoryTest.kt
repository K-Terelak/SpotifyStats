package kt.mobile.spotify_stats.feature_artist.domain.repository

import android.content.SharedPreferences
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.UnitTest
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.core.data.dto.response.ExternalUrls
import kt.mobile.spotify_stats.core.data.dto.response.Followers
import kt.mobile.spotify_stats.core.data.dto.response.get_top_item.ArtistItem
import kt.mobile.spotify_stats.feature_artist.data.remote.ArtistApi
import kt.mobile.spotify_stats.feature_artist.data.remote.response.ArtistsTopTracksResponse
import kt.mobile.spotify_stats.feature_artist.data.remote.response.get_related.RelatedArtistsResponse
import kt.mobile.spotify_stats.feature_artist.data.repository.ArtistRepositoryImpl
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@ExperimentalCoroutinesApi
class ArtistRepositoryTest : UnitTest() {


    private lateinit var artistRepository: ArtistRepository

    @MockK
    private lateinit var artistApi: ArtistApi

    @MockK
    private lateinit var sharedPreferences: SharedPreferences

    @MockK
    private lateinit var artistItemResponse: Response<ArtistItem>

    @MockK
    private lateinit var artistsTopTracksResponse: Response<ArtistsTopTracksResponse>

    @MockK
    private lateinit var relatedArtistsResponse: Response<RelatedArtistsResponse>

    @Before
    fun setUp() {
        sharedPreferences = mockk(relaxed = true)
        artistRepository = ArtistRepositoryImpl(artistApi, sharedPreferences)
    }


    /**
     * getArtist
     */
    @Test
    fun `getArtist(id), return success without body`() = runTest {
        every { artistItemResponse.isSuccessful } returns true
        every { artistItemResponse.body() } returns null
        coEvery { artistApi.getArtist(anyString()) } returns artistItemResponse

        val artist = artistRepository.getArtist(anyString())
        val expected = Resource.Error(R.string.empty, null)
        assertThat(artist.error).isEqualTo(expected.error)
    }

    @Test
    fun `getArtist(id), return success with body`() = runTest {
        val dummyArtistItem = ArtistItem(
            ExternalUrls(anyString()),
            Followers(anyString(), ArgumentMatchers.anyInt()),
            anyList(),
            anyString(),
            anyString(),
            anyList(),
            anyString(),
            ArgumentMatchers.anyInt(),
            anyString(),
            anyString()
        )

        every { artistItemResponse.isSuccessful } returns true
        every { artistItemResponse.body() } returns dummyArtistItem
        coEvery { artistApi.getArtist(anyString()) } returns artistItemResponse

        val artist = artistRepository.getArtist(anyString())
        val expected = Resource.Success(dummyArtistItem)
        assertThat(artist.data).isEqualTo(expected.data)
    }


    @Test
    fun `getArtist(id), return error without errorBody`() = runTest {
        every { artistItemResponse.isSuccessful } returns false
        every { artistItemResponse.errorBody() } returns null
        coEvery { artistApi.getArtist(anyString()) } returns artistItemResponse

        val artist = artistRepository.getArtist(anyString())
        val expected = Resource.Error(R.string.unknown_error, null)
        assertThat(artist.error).isEqualTo(expected.error)
    }

    @Test
    fun `getArtist(id), return error with errorBody`() = runTest {
        every { artistItemResponse.isSuccessful } returns false
        every { artistItemResponse.errorBody() } returns anyString().toResponseBody()
        coEvery { artistApi.getArtist(anyString()) } returns artistItemResponse

        val artist = artistRepository.getArtist(anyString())
        val expected = Resource.Error(R.string.couldnt_load, null)
        assertThat(artist.error).isEqualTo(expected.error)
    }

    @Test
    fun `getArtist(id), return IOException`() = runTest {
        coEvery { artistApi.getArtist(anyString()) }.throws(IOException())

        val artist = artistRepository.getArtist(anyString())
        val expected = Resource.Error(error = R.string.couldnt_reach_server, data = null)
        assertThat(artist.error).isEqualTo(expected.error)
    }

    @Test
    fun `getArtist(id), return HTTPException`() = runTest {
        coEvery { artistApi.getArtist(anyString()) }
            .throws(
                HttpException(
                    Response.error<ArtistItem>(400, anyString().toResponseBody())
                )
            )

        val artist = artistRepository.getArtist(anyString())
        val expected = Resource.Error(error = R.string.couldnt_load, data = null)
        assertThat(artist.error).isEqualTo(expected.error)
    }


    /**
     * getArtistsTopTracks
     */
    @Test
    fun `getArtistsTopTracks(id), return success without body`() = runTest {
        val market = "PL"

        every { sharedPreferences.getString("country", any()) } returns market
        every { artistsTopTracksResponse.isSuccessful } returns true
        every { artistsTopTracksResponse.body() } returns null
        coEvery { artistApi.getArtistTopTracks(anyString(), market) } returns artistsTopTracksResponse

        val artistTopTracks = artistRepository.getArtistsTopTracks(anyString())
        val expected = Resource.Error(R.string.empty, null)
        assertThat(artistTopTracks.error).isEqualTo(expected.error)
    }

    @Test
    fun `getArtistsTopTracks(id), return success with body`() = runTest {
        val market = "PL"
        val artistTopTracksDummy = ArtistsTopTracksResponse(anyList())

        every { sharedPreferences.getString("country", any()) } returns market
        every { artistsTopTracksResponse.isSuccessful } returns true
        every { artistsTopTracksResponse.body() } returns artistTopTracksDummy
        coEvery { artistApi.getArtistTopTracks(anyString(), market) } returns artistsTopTracksResponse

        val artistTopTracks = artistRepository.getArtistsTopTracks(anyString())
        val expected = Resource.Success(artistTopTracksDummy)
        assertThat(artistTopTracks.data).isEqualTo(expected.data)
    }

    @Test
    fun `getArtistsTopTracks(id), return error empty market`() = runTest {
        val market = ""

        every { sharedPreferences.getString("country", any()) } returns market
        every { artistsTopTracksResponse.isSuccessful } returns false
        every { artistsTopTracksResponse.body() } returns null
        coEvery { artistApi.getArtistTopTracks(anyString(), market) } returns artistsTopTracksResponse

        val artistTopTracks = artistRepository.getArtistsTopTracks(anyString())
        val expected = Resource.Error(R.string.no_country_code, null)
        assertThat(artistTopTracks.error).isEqualTo(expected.error)
    }

    @Test
    fun `getArtistsTopTracks(id), return error without errorBody`() = runTest {
        val market = "PL"

        every { sharedPreferences.getString("country", any()) } returns market
        every { artistsTopTracksResponse.isSuccessful } returns false
        every { artistsTopTracksResponse.errorBody() } returns null
        coEvery { artistApi.getArtistTopTracks(anyString(), market) } returns artistsTopTracksResponse

        val artistTopTracks = artistRepository.getArtistsTopTracks(anyString())
        val expected = Resource.Error(error = R.string.unknown_error, data = null)
        assertThat(artistTopTracks.error).isEqualTo(expected.error)
    }

    @Test
    fun `getArtistsTopTracks(id), return error with errorBody`() = runTest {
        val market = "PL"

        every { sharedPreferences.getString("country", any()) } returns market
        every { artistsTopTracksResponse.isSuccessful } returns false
        every { artistsTopTracksResponse.errorBody() } returns anyString().toResponseBody()
        coEvery { artistApi.getArtistTopTracks(anyString(), market) } returns artistsTopTracksResponse

        val artistTopTracks = artistRepository.getArtistsTopTracks(anyString())
        val expected = Resource.Error(error = R.string.couldnt_load, data = null)
        assertThat(artistTopTracks.error).isEqualTo(expected.error)
    }

    @Test
    fun `getArtistsTopTracks(id), return IOException`() = runTest {
        val market = "PL"

        every { sharedPreferences.getString("country", any()) } returns market
        coEvery {
            artistApi.getArtistTopTracks(anyString(), market)
        }.throws(IOException())

        val artistTopTracks = artistRepository.getArtistsTopTracks(id = anyString())
        val expected = Resource.Error(error = R.string.couldnt_reach_server, data = null)
        assertThat(artistTopTracks.error).isEqualTo(expected.error)
    }

    @Test
    fun `getArtistsTopTracks(id), return HTTPException`() = runTest {
        val market = "PL"

        every { sharedPreferences.getString("country", any()) } returns market
        coEvery { artistApi.getArtistTopTracks(anyString(), market) }
            .throws(
                HttpException(
                    Response.error<ArtistsTopTracksResponse>(400, anyString().toResponseBody())
                )
            )

        val artistTopTracks = artistRepository.getArtistsTopTracks(anyString())
        val expected = Resource.Error(error = R.string.couldnt_load, data = null)
        assertThat(artistTopTracks.error).isEqualTo(expected.error)
    }


    /**
     * getRelatedArtists
     */
    @Test
    fun `getRelatedArtists(id), return success without body`() = runTest {
        every { relatedArtistsResponse.isSuccessful } returns true
        every { relatedArtistsResponse.body() } returns null
        coEvery { artistApi.getRelatedArtists(anyString()) } returns relatedArtistsResponse

        val relatedArtists = artistRepository.getRelatedArtists(anyString())
        val expected = Resource.Error(R.string.empty, null)
        assertThat(relatedArtists.error).isEqualTo(expected.error)
    }

    @Test
    fun `getRelatedArtists(id), return success with body`() = runTest {
        val relatedArtistDummy = RelatedArtistsResponse(anyList())

        every { relatedArtistsResponse.isSuccessful } returns true
        every { relatedArtistsResponse.body() } returns relatedArtistDummy
        coEvery { artistApi.getRelatedArtists(anyString()) } returns relatedArtistsResponse

        val relatedArtists = artistRepository.getRelatedArtists(anyString())
        val expected = Resource.Success(relatedArtistDummy)
        assertThat(relatedArtists.data).isEqualTo(expected.data)
    }


    @Test
    fun `getRelatedArtists(id), return error without errorBody`() = runTest {
        every { relatedArtistsResponse.isSuccessful } returns false
        every { relatedArtistsResponse.errorBody() } returns null
        coEvery { artistApi.getRelatedArtists(anyString()) } returns relatedArtistsResponse

        val relatedArtists = artistRepository.getRelatedArtists(anyString())
        val expected = Resource.Error(R.string.unknown_error, null)
        assertThat(relatedArtists.error).isEqualTo(expected.error)
    }

    @Test
    fun `getRelatedArtists(id), return error with errorBody`() = runTest {
        every { relatedArtistsResponse.isSuccessful } returns false
        every { relatedArtistsResponse.errorBody() } returns anyString().toResponseBody()
        coEvery { artistApi.getRelatedArtists(anyString()) } returns relatedArtistsResponse

        val relatedArtists = artistRepository.getRelatedArtists(anyString())
        val expected = Resource.Error(R.string.couldnt_load, null)
        assertThat(relatedArtists.error).isEqualTo(expected.error)
    }

    @Test
    fun `getRelatedArtists(id), return IOException`() = runTest {
        coEvery { artistApi.getRelatedArtists(anyString()) }.throws(IOException())

        val relatedArtists = artistRepository.getRelatedArtists(anyString())
        val expected = Resource.Error(error = R.string.couldnt_reach_server, data = null)
        assertThat(relatedArtists.error).isEqualTo(expected.error)
    }

    @Test
    fun `getRelatedArtists(id), return HTTPException`() = runTest {
        coEvery { artistApi.getRelatedArtists(anyString()) }
            .throws(
                HttpException(
                    Response.error<RelatedArtistsResponse>(400, anyString().toResponseBody())
                )
            )

        val relatedArtists = artistRepository.getRelatedArtists(anyString())
        val expected = Resource.Error(error = R.string.couldnt_load, data = null)
        assertThat(relatedArtists.error).isEqualTo(expected.error)
    }


    @After
    internal fun tearDown() {
        unmockkAll()
    }

}