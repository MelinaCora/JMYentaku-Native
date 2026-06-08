package com.jmyentaku.app.viewmodel.added

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmyentaku.app.data.model.ManualManga
import com.jmyentaku.app.data.repository.ManualMangaRepository
import kotlinx.coroutines.launch

class AddedMangaViewModel : ViewModel() {

    private val repository = ManualMangaRepository()

    var mangas = mutableStateOf<List<ManualManga>>(emptyList())
        private set

    init {
        loadMangas()
    }

    fun loadMangas() {
        viewModelScope.launch {
            mangas.value = repository.getManualMangas()
        }
    }
}