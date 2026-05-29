package com.jmyentaku.app.viewmodel.bookmarks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmyentaku.app.data.firebase.AnimeListRepository
import com.jmyentaku.app.viewmodel.bookmarks.state.BookmarksUiState
import kotlinx.coroutines.launch

class BookmarksViewModel : ViewModel() {

    private val repository = AnimeListRepository()

    var uiState by mutableStateOf(
        BookmarksUiState()
    )
        private set

    init {

        getAnimeByStatus(
            "in_progress"
        )
    }

    fun getAnimeByStatus(
        status: String
    ) {

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true,
                selectedStatus = status
            )

            repository.getAnimeByStatus(status)

                .onSuccess { animeList ->

                    uiState = uiState.copy(
                        animeList = animeList,
                        isLoading = false
                    )
                }

                .onFailure { exception ->

                    uiState = uiState.copy(
                        error = exception.message,
                        isLoading = false
                    )
                }
        }
    }
}