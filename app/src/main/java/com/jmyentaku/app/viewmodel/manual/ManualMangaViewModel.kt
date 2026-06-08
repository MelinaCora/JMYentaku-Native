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

    private val repository =
        ManualMangaRepository()

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

    fun updateTitle(value: String) {
        title = value
    }

    fun updateAuthor(value: String) {
        author = value
    }

    fun updateChapters(value: String) {
        chapters = value
    }

    fun updateGenre(value: String) {
        genre = value
    }

    fun updateDescription(value: String) {
        description = value
    }

    fun saveManualManga(
        onSuccess: () -> Unit
    ) {

        viewModelScope.launch {

            val manga = ManualManga(

                title = title,

                author = author,

                chapters = chapters.toIntOrNull() ?: 0,

                genre = genre,

                description = description
            )

            repository.saveManualManga(
                manga
            )

            onSuccess()
        }
    }
}