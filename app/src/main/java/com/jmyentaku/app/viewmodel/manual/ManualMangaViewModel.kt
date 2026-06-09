package com.jmyentaku.app.viewmodel.manual

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.jmyentaku.app.data.model.ManualManga
import com.jmyentaku.app.data.repository.ManualMangaRepository

import kotlinx.coroutines.launch

class ManualMangaViewModel : ViewModel() {

    private val repository = ManualMangaRepository()

    // 📚 LISTA DE MANGAS (FALTABA ESTO)
    var mangas = mutableStateOf<List<ManualManga>>(emptyList())
        private set

    var title by mutableStateOf("")
        private set

    var author by mutableStateOf("")
        private set

    var chapters by mutableStateOf("")
        private set

    var genre by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    // 🔄 UPDATE INPUTS
    fun updateTitle(value: String) { title = value }
    fun updateAuthor(value: String) { author = value }
    fun updateChapters(value: String) { chapters = value }
    fun updateGenre(value: String) { genre = value }
    fun updateDescription(value: String) { description = value }

    // 💾 SAVE
    fun saveManualManga(onSuccess: () -> Unit) {

        viewModelScope.launch {

            val manga = ManualManga(
                title = title,
                author = author,
                chapters = chapters.toIntOrNull() ?: 0,
                genre = genre,
                description = description
            )

            repository.saveManualManga(manga)

            loadMangas()
            onSuccess()
        }
    }


    fun loadMangas() {
        viewModelScope.launch {
            mangas.value = repository.getManualMangas()
        }
    }

    // TRACKER
    fun updateStatus(manga: ManualManga, newStatus: String) {
        viewModelScope.launch {
            val updated = manga.copy(status = newStatus)
            repository.updateManga(updated)
            loadMangas()
        }
    }

    fun addChapter(manga: ManualManga) {
        viewModelScope.launch {
            val updated = manga.copy(
                currentChapter = manga.currentChapter + 1
            )
            repository.updateManga(updated)
            loadMangas()
        }
    }

    fun removeChapter(manga: ManualManga) {
        viewModelScope.launch {
            val updated = manga.copy(
                currentChapter = (manga.currentChapter - 1).coerceAtLeast(0)
            )
            repository.updateManga(updated)
            loadMangas()
        }
    }
}