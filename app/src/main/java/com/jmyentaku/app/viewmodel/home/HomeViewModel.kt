package com.jmyentaku.app.viewmodel.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmyentaku.app.data.repository.AnimeRepository
import com.jmyentaku.app.data.repository.MangaRepository
import com.jmyentaku.app.viewmodel.home.state.HomeUiState
import kotlinx.coroutines.launch
import com.jmyentaku.app.data.repository.VoiceActorRepository
import com.jmyentaku.app.data.firebase.AuthRepository

class HomeViewModel : ViewModel() {

    // Repository de animes
    private val repository = AnimeRepository()

    // Repository de mangas
    private val mangaRepository = MangaRepository()

    //Repository de actores
    private val voiceActorRepository = VoiceActorRepository()

    //Repository de Auth
    private val authRepository = AuthRepository()

    var uiState by mutableStateOf(
        HomeUiState()
    )
        private set

    init {

        getHomeData()
    }

    private fun getHomeData() {

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true
            )

            try {

                // Traer animes
                val animeList =
                    repository.getTopAnime()

                // Traer mangas
                val mangaList =
                    mangaRepository.getTopManga()

                //traer actores
                val voiceActorList =
                    voiceActorRepository.getTopVoiceActors()

                uiState = uiState.copy(
                    animeList = animeList,
                    mangas = mangaList,
                    voiceActors = voiceActorList,
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

    fun logout() {

        authRepository.logout()
    }
}