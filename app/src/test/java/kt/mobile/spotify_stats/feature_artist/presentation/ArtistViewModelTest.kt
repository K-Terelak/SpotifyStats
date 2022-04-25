package kt.mobile.spotify_stats.feature_artist.presentation

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
import kt.mobile.spotify_stats.core.data.dto.response.ExternalUrls
import kt.mobile.spotify_stats.core.data.dto.response.Followers
import kt.mobile.spotify_stats.core.data.dto.response.get_top_item.ArtistItem
import kt.mobile.spotify_stats.core.util.Constants.ARTIST_ID
import kt.mobile.spotify_stats.feature_artist.data.remote.response.ArtistsTopTracksResponse
import kt.mobile.spotify_stats.feature_artist.data.remote.response.get_related.RelatedArtistsResponse
import kt.mobile.spotify_stats.feature_artist.domain.use_case.ArtistUseCases
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.*

@ExperimentalCoroutinesApi
class ArtistViewModelTest : UnitTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var artistUseCases: ArtistUseCases

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle

    @MockK
    private lateinit var artistViewModel: ArtistViewModel

    @MockK
    private lateinit var state: ArtistState

    @Test
    fun `null savedStateHandle, state default values`() = runTest {
        every { savedStateHandle.get<String>(ARTIST_ID) } returns null
        artistViewModel = ArtistViewModel(artistUseCases, savedStateHandle)

        state = artistViewModel.artistState.value

        assertThat(state.isArtistLoading).isFalse()
        assertThat(state.artist).isNull()
        assertThat(state.artistError).isNull()

        assertThat(state.isArtistsTopTracksLoading).isFalse()
        assertThat(state.artistsTopTracks).isNull()
        assertThat(state.artistsTopTracksError).isNull()

        assertThat(state.isRelatedArtistsLoading).isFalse()
        assertThat(state.relatedArtists).isNull()
        assertThat(state.relatedArtistsError).isNull()
    }

    @Test
    fun `savedStateHandle not null, init fun return Resource error`() = runTest {
        val artistId = "id"

        every { savedStateHandle.get<String>(ARTIST_ID) } returns artistId
        coEvery { artistUseCases.getArtist(artistId) }.answers { Resource.Error(anyInt()) }
        coEvery { artistUseCases.getArtistsTopTracks(artistId) }.answers { Resource.Error(anyInt()) }
        coEvery { artistUseCases.getRelatedArtists(artistId) }.answers { Resource.Error(anyInt()) }
        artistViewModel = ArtistViewModel(artistUseCases, savedStateHandle)

        state = artistViewModel.artistState.value

        assertThat(state.isArtistLoading).isFalse()
        assertThat(state.artist).isNull()
        assertThat(state.artistError).isNotNull()

        assertThat(state.isArtistsTopTracksLoading).isFalse()
        assertThat(state.artistsTopTracks).isNull()
        assertThat(state.artistsTopTracksError).isNotNull()

        assertThat(state.isRelatedArtistsLoading).isFalse()
        assertThat(state.relatedArtists).isNull()
        assertThat(state.relatedArtistsError).isNotNull()
    }


    @Test
    fun `savedStateHandle not null, init fun return Resource success`() = runTest {
        val dummyArtistItem = ArtistItem(
            ExternalUrls(anyString()),
            Followers(anyString(), anyInt()),
            anyList(),
            anyString(),
            anyString(),
            anyList(),
            anyString(),
            anyInt(),
            anyString(),
            anyString()
        )
        val dummyArtistsTopTracks = ArtistsTopTracksResponse(anyList())
        val dummyRelatedArtists = RelatedArtistsResponse(anyList())
        val artistId = "id"

        every { savedStateHandle.get<String>(ARTIST_ID) } returns artistId
        coEvery { artistUseCases.getArtist(artistId) }.answers { Resource.Success(dummyArtistItem) }
        coEvery { artistUseCases.getArtistsTopTracks(artistId) }.answers { Resource.Success(dummyArtistsTopTracks) }
        coEvery { artistUseCases.getRelatedArtists(artistId) }.answers { Resource.Success(dummyRelatedArtists) }
        artistViewModel = ArtistViewModel(artistUseCases, savedStateHandle)

        state = artistViewModel.artistState.value

        assertThat(state.isArtistLoading).isFalse()
        assertThat(state.artist).isNotNull()
        assertThat(state.artistError).isNull()

        assertThat(state.isArtistsTopTracksLoading).isFalse()
        assertThat(state.artistsTopTracks).isNotNull()
        assertThat(state.artistsTopTracksError).isNull()

        assertThat(state.isRelatedArtistsLoading).isFalse()
        assertThat(state.relatedArtists).isNotNull()
        assertThat(state.relatedArtistsError).isNull()
    }


    @After
    fun tearDown() {
        clearAllMocks()
    }
}