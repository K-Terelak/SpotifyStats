package kt.mobile.spotify_stats.feature_track.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kt.mobile.spotify_stats.MainCoroutineRule
import kt.mobile.spotify_stats.UnitTest
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.core.data.dto.response.Album
import kt.mobile.spotify_stats.core.data.dto.response.ExternalUrls
import kt.mobile.spotify_stats.core.util.Constants
import kt.mobile.spotify_stats.feature_top.domain.models.TracksFeatures
import kt.mobile.spotify_stats.feature_track.domain.models.MyTrack
import kt.mobile.spotify_stats.feature_track.domain.use_case.TrackUseCases
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.*

@ExperimentalCoroutinesApi
class TrackViewModelTest : UnitTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var trackUseCases: TrackUseCases

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle

    @MockK
    private lateinit var trackViewModel: TrackViewModel

    @MockK
    private lateinit var state: TrackState

    @Test
    fun `null savedStateHandle, state default values`() = runTest {
        every { savedStateHandle.get<String>(Constants.TRACK_ID) } returns null
        trackViewModel = TrackViewModel(trackUseCases, savedStateHandle)

        state = trackViewModel.trackState.value

        assertThat(state.isTrackLoading).isFalse()
        assertThat(state.track).isNull()
        assertThat(state.trackError).isNull()

        assertThat(state.isTrackFeaturesLoading).isFalse()
        assertThat(state.trackFeatures).isNull()
        assertThat(state.trackFeaturesError).isNull()
    }

    @Test
    fun `savedStateHandle not null, init fun return Resource error`() = runTest {
        val trackId = anyString()

        every { savedStateHandle.get<String>(Constants.TRACK_ID) } returns trackId
        coEvery { trackUseCases.getTrack(trackId) }.answers { Resource.Error(anyInt()) }
        coEvery { trackUseCases.getTrackFeatures(trackId) }.answers { Resource.Error(anyInt()) }
        trackViewModel = TrackViewModel(trackUseCases, savedStateHandle)

        state = trackViewModel.trackState.value

        assertThat(state.isTrackLoading).isFalse()
        assertThat(state.track).isNull()
        assertThat(state.trackError).isNotNull()

        assertThat(state.isTrackFeaturesLoading).isFalse()
        assertThat(state.trackFeatures).isNull()
        assertThat(state.trackFeaturesError).isNotNull()
    }

    @Test
    fun `savedStateHandle not null, init fun return Resource success`() = runTest {
        val dummyTrackItem = MyTrack(
            id = anyString(),
            name = anyString(),
            popularity = anyInt(),
            album = Album(
                albumType = anyString(),
                artists = anyList(),
                availableMarkets = anyList(),
                externalUrls = ExternalUrls(spotify = anyString()),
                href = anyString(),
                id = anyString(),
                images = anyList(),
                name = anyString(),
                releaseDate = anyString(),
                releaseDatePrecision = anyString(),
                totalTracks = anyInt(),
                type = anyString(),
                uri = anyString()
            ),
            artists = anyList(),
            durationMs = anyInt()
        )
        val dummyTrackFeatures = TracksFeatures(
            listFeatures = anyList()
        )
        val trackId = anyString()

        every { savedStateHandle.get<String>(Constants.TRACK_ID) } returns trackId
        coEvery { trackUseCases.getTrack(trackId) }.answers { Resource.Success(dummyTrackItem) }
        coEvery { trackUseCases.getTrackFeatures(trackId) }.answers { Resource.Success(dummyTrackFeatures) }
        trackViewModel = TrackViewModel(trackUseCases, savedStateHandle)

        state = trackViewModel.trackState.value

        assertThat(state.isTrackLoading).isFalse()
        assertThat(state.track).isEqualTo(dummyTrackItem)
        assertThat(state.trackError).isNull()

        assertThat(state.isTrackFeaturesLoading).isFalse()
        assertThat(state.trackFeatures).isEqualTo(dummyTrackFeatures)
        assertThat(state.trackFeaturesError).isNull()
    }


    @After
    fun tearDown() {
        clearAllMocks()
    }
}