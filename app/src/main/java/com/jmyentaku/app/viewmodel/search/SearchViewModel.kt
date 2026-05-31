package com.jmyentaku.app.viewmodel.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmyentaku.app.data.network.RetrofitInstance
import com.jmyentaku.app.viewmodel.search.state.SearchUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private var searchJob: kotlinx.coroutines.Job? = null

    fun search(query: String) {
        if (query.length < 2) {
            clearResults()
            return
        }

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            delay(500)

            try {
                val animeResponse = RetrofitInstance.api.searchAnime(query)
                val mangaResponse = RetrofitInstance.api.searchManga(query)
                val peopleResponse = RetrofitInstance.api.searchPeople(query)

                _uiState.value = _uiState.value.copy(
                    animes = animeResponse.data,
                    mangas = mangaResponse.data,
                    voiceActors = peopleResponse.data,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun clearResults() {
        _uiState.value = SearchUiState()
    }
}