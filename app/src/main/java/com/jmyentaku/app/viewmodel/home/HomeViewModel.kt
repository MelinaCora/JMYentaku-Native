package com.jmyentaku.app.viewmodel.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmyentaku.app.data.repository.AnimeRepository
import com.jmyentaku.app.viewmodel.home.state.HomeUiState
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = AnimeRepository()

    var uiState by mutableStateOf(
        HomeUiState()
    )
        private set

    init {

        getTopAnime()
    }

    private fun getTopAnime() {

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true
            )

            try {

                val animeList =
                    repository.getTopAnime()

                uiState = uiState.copy(
                    animeList = animeList,
                    isLoading = false
                )

            } catch (e: Exception) {

                uiState = uiState.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }
}