package kt.mobile.spotify_stats.feature_track.domain.repository

import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kt.mobile.spotify_stats.R
import kt.mobile.spotify_stats.UnitTest
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.core.data.dto.response.Album
import kt.mobile.spotify_stats.core.data.dto.response.ExternalIds
import kt.mobile.spotify_stats.core.data.dto.response.ExternalUrls
import kt.mobile.spotify_stats.core.data.dto.response.get_top_item.ArtistItem
import kt.mobile.spotify_stats.feature_top.data.remote.response.get_tracks_features.TracksFeaturesResponse
import kt.mobile.spotify_stats.feature_track.data.remote.TrackApi
import kt.mobile.spotify_stats.feature_track.data.remote.response.get_track.TrackResponse
import kt.mobile.spotify_stats.feature_track.data.repository.TrackRepositoryImpl
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.*
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


@ExperimentalCoroutinesApi
class TrackRepositoryTest : UnitTest() {

    private lateinit var trackRepository: TrackRepository

    @MockK
    private lateinit var trackApi: TrackApi

    @MockK
    private lateinit var trackItemResponse: Response<TrackResponse>

    @MockK
    private lateinit var trackItemFeaturesResponse: Response<TracksFeaturesResponse>

    @Before
    fun setUp() {
        trackRepository = TrackRepositoryImpl(trackApi)
    }


    /**
     * GetTrack
     */
    @Test
    fun `getTrack(id), return success without body`() = runTest {
        every { trackItemResponse.isSuccessful } returns true
        every { trackItemResponse.body() } returns null
        coEvery { trackApi.getTrack(anyString()) } returns trackItemResponse

        val track = trackRepository.getTrack(anyString())
        val expected = Resource.Error(R.string.empty, null)
        assertThat(track.error).isEqualTo(expected.error)
    }

    @Test
    fun `getTrack(id), return success with body`() = runTest {
        val dummyTrack = TrackResponse(
            Album(
                anyString(),
                anyList(),
                anyList(),
                ExternalUrls(anyString()),
                anyString(),
                anyString(),
                anyList(),
                anyString(),
                anyString(),
                anyString(),
                anyInt(),
                anyString(),
                anyString()
            ),
            anyList(),
            anyList(),
            anyInt(),
            anyInt(),
            anyBoolean(),
            ExternalIds(anyString()),
            ExternalUrls(anyString()),
            anyString(),
            anyString(),
            anyBoolean(),
            anyString(),
            anyInt(),
            anyString(),
            anyInt(),
            anyString(),
            anyString()
        )

        every { trackItemResponse.isSuccessful } returns true
        every { trackItemResponse.body() } returns dummyTrack
        coEvery { trackApi.getTrack(anyString()) } returns trackItemResponse

        val track = trackRepository.getTrack(anyString())
        val expected = Resource.Success(dummyTrack.toTrack())
        assertThat(track.data).isEqualTo(expected.data)
    }


    @Test
    fun `getTrack(id), return error without errorBody`() = runTest {
        every { trackItemResponse.isSuccessful } returns false
        every { trackItemResponse.errorBody() } returns null
        coEvery { trackApi.getTrack(anyString()) } returns trackItemResponse

        val artist = trackRepository.getTrack(anyString())
        val expected = Resource.Error(R.string.unknown_error, null)
        assertThat(artist.error).isEqualTo(expected.error)
    }

    @Test
    fun `getTrack(id), return error with errorBody`() = runTest {
        every { trackItemResponse.isSuccessful } returns false
        every { trackItemResponse.errorBody() } returns anyString().toResponseBody()
        coEvery { trackApi.getTrack(anyString()) } returns trackItemResponse

        val artist = trackRepository.getTrack(anyString())
        val expected = Resource.Error(R.string.couldnt_load, null)
        assertThat(artist.error).isEqualTo(expected.error)
    }

    @Test
    fun `getTrack(id), return IOException`() = runTest {
        coEvery { trackApi.getTrack(anyString()) }.throws(IOException())

        val artist = trackRepository.getTrack(anyString())
        val expected = Resource.Error(error = R.string.couldnt_reach_server, data = null)
        assertThat(artist.error).isEqualTo(expected.error)
    }

    @Test
    fun `getTrack(id), return HTTPException`() = runTest {
        coEvery { trackApi.getTrack(anyString()) }
            .throws(
                HttpException(
                    Response.error<ArtistItem>(400, anyString().toResponseBody())
                )
            )

        val artist = trackRepository.getTrack(anyString())
        val expected = Resource.Error(error = R.string.couldnt_load, data = null)
        assertThat(artist.error).isEqualTo(expected.error)
    }


    /**
     * getTrackFeatures
     */
    @Test
    fun `getTrackFeatures(ids), return success without body`() = runTest {
        every { trackItemFeaturesResponse.isSuccessful } returns true
        every { trackItemFeaturesResponse.body() } returns null
        coEvery { trackApi.getTrackFeatures(anyString()) } returns trackItemFeaturesResponse

        val track = trackRepository.getTrackFeatures(anyString())
        val expected = Resource.Error(R.string.empty, null)
        assertThat(track.error).isEqualTo(expected.error)
    }

    @Test
    fun `getTrackFeatures(ids), return success with body`() = runTest {
        val dummyTrackFeaturesResponse = TracksFeaturesResponse(
            anyList()
        )

        every { trackItemFeaturesResponse.isSuccessful } returns true
        every { trackItemFeaturesResponse.body() } returns dummyTrackFeaturesResponse
        coEvery { trackApi.getTrackFeatures(anyString()) } returns trackItemFeaturesResponse

        val track = trackRepository.getTrackFeatures(anyString())
        val expected = Resource.Success(dummyTrackFeaturesResponse.toTracksFeatures())
        assertThat(track.data).isEqualTo(expected.data)
    }


    @Test
    fun `getTrackFeatures(ids), return error without errorBody`() = runTest {
        every { trackItemFeaturesResponse.isSuccessful } returns false
        every { trackItemFeaturesResponse.errorBody() } returns null
        coEvery { trackApi.getTrackFeatures(anyString()) } returns trackItemFeaturesResponse

        val artist = trackRepository.getTrackFeatures(anyString())
        val expected = Resource.Error(R.string.unknown_error, null)
        assertThat(artist.error).isEqualTo(expected.error)
    }

    @Test
    fun `getTrackFeatures(ids), return error with errorBody`() = runTest {
        every { trackItemFeaturesResponse.isSuccessful } returns false
        every { trackItemFeaturesResponse.errorBody() } returns anyString().toResponseBody()
        coEvery { trackApi.getTrackFeatures(anyString()) } returns trackItemFeaturesResponse

        val artist = trackRepository.getTrackFeatures(anyString())
        val expected = Resource.Error(R.string.couldnt_load, null)
        assertThat(artist.error).isEqualTo(expected.error)
    }

    @Test
    fun `getTrackFeatures(ids), return IOException`() = runTest {
        coEvery { trackApi.getTrackFeatures(anyString()) }.throws(IOException())

        val artist = trackRepository.getTrackFeatures(anyString())
        val expected = Resource.Error(error = R.string.couldnt_reach_server, data = null)
        assertThat(artist.error).isEqualTo(expected.error)
    }

    @Test
    fun `getTrackFeatures(ids), return HTTPException`() = runTest {
        coEvery { trackApi.getTrackFeatures(anyString()) }
            .throws(
                HttpException(
                    Response.error<ArtistItem>(400, anyString().toResponseBody())
                )
            )

        val artist = trackRepository.getTrackFeatures(anyString())
        val expected = Resource.Error(error = R.string.couldnt_load, data = null)
        assertThat(artist.error).isEqualTo(expected.error)
    }

    @After
    internal fun tearDown() {
        unmockkAll()
    }
}