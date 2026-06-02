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
import com.jmyentaku.app.data.model.Anime
//temporal para probar el firebase
import com.jmyentaku.app.data.firebase.AnimeListRepository
import com.jmyentaku.app.data.model.UserAnime

class HomeViewModel : ViewModel() {

    // Repository de animes
    private val repository = AnimeRepository()

    // Repository de mangas
    private val mangaRepository = MangaRepository()

    //Repository de actores
    private val voiceActorRepository = VoiceActorRepository()

    //Repository de Auth
    private val authRepository = AuthRepository()

    //Repositoryy temporal de listas de animes
    private val animeListRepository = AnimeListRepository()

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

    // Para guardar anime
    fun saveAnimeStatus(anime: Anime, status: String) {
        viewModelScope.launch {
            animeListRepository.saveAnime(
                UserAnime(
                    animeId = anime.mal_id,
                    title = anime.title,
                    imageUrl = anime.images.jpg.image_url,
                    type = "anime",
                    status = status
                )
            )
        }
    }

    fun saveMangaStatus(manga: Anime, status: String) { // manga es Anime pero es manga
        viewModelScope.launch {
            animeListRepository.saveAnime(
                UserAnime(
                    animeId = manga.mal_id,
                    title = manga.title,
                    imageUrl = manga.images.jpg.image_url,
                    type = "manga", //especifico
                    status = status
                )
            )
        }
    }

    fun saveAnimeDetailStatus(
        animeId: Int,
        title: String,
        imageUrl: String,
        totalEpisodes: Int,
        status: String
    ) {
        viewModelScope.launch {

            animeListRepository.saveAnime(

                UserAnime(
                    animeId = animeId,
                    title = title,
                    imageUrl = imageUrl,
                    type = "anime",
                    status = status,
                    progress = 0,
                    total = totalEpisodes
                )
            )
        }
    }

    fun saveMangaDetailStatus(
        mangaId: Int,
        title: String,
        imageUrl: String,
        totalChapters: Int,
        status: String
    ) {
        viewModelScope.launch {

            animeListRepository.saveAnime(

                UserAnime(
                    animeId = mangaId,
                    title = title,
                    imageUrl = imageUrl,
                    type = "manga",
                    status = status,
                    progress = 0,
                    total = totalChapters
                )
            )
        }
    }

    fun updateProgress(
        animeId: Int,
        newProgress: Int
    ) {

        viewModelScope.launch {

            animeListRepository.updateProgress(

                animeId = animeId,

                newProgress = newProgress
            )
        }
    }

    fun getUserAnime(
        animeId: Int,
        onResult: (UserAnime?) -> Unit
    ) {

        viewModelScope.launch {

            val result =
                animeListRepository.getUserAnime(animeId)

            onResult(
                result.getOrNull()
            )
        }
    }
}