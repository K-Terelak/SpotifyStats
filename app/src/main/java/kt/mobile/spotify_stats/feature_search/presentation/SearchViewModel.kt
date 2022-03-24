package kt.mobile.spotify_stats.feature_search.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kt.mobile.spotify_stats.core.data.Resource
import kt.mobile.spotify_stats.feature_search.domain.models.MySearchItem
import kt.mobile.spotify_stats.feature_search.domain.use_case.SearchUseCases
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases
) : ViewModel() {

    private val _searchState = mutableStateOf(SearchState())
    val searchState: State<SearchState> = _searchState

    private var searchJob: Job? = null


    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.Query -> {
                searchUser(event.query)
            }
        }
    }

    private fun searchUser(query: String) {
        _searchState.value = _searchState.value.copy(query = query)
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            delay(800L)
            _searchState.value = searchState.value.copy(query = query)

            searchItems(q = query)
        }
    }


    private fun searchItems(q: String) {

        viewModelScope.launch {

            _searchState.value = searchState.value.copy(
                isItemsLoading = true,
                itemsError = ""
            )

            val searchItems: MutableList<MySearchItem> = mutableListOf()

            when (val result = searchUseCases.searchArtists(q = q)) {
                is Resource.Success -> {
                    Log.d("searchArtists", "SUCCESS")
                    result.data?.searchItems?.let { searchItems.addAll(it) }
                }
                is Resource.Error -> {
                    Log.e("searchArtists", "ERROR ${result.error}")
                    _searchState.value = searchState.value.copy(
                        itemsError = result.error ?: "Unknown error"
                    )

                }
            }

            when (val result = searchUseCases.searchTracks(q = q)) {
                is Resource.Success -> {
                    Log.d("searchTracks", "SUCCESS")
                    result.data?.searchItems?.let { searchItems.addAll(it) }
                }
                is Resource.Error -> {
                    Log.e("searchTracks", "ERROR ${result.error}")
                    _searchState.value =
                        searchState.value.copy(
                            itemsError = result.error ?: "Unknown error"
                        )
                }
            }

            _searchState.value = searchState.value.copy(
                searchItems = searchItems,
                isItemsLoading = false
            )
        }
    }
}