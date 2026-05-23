package com.jmyentaku.app.viewmodel.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmyentaku.app.data.repository.DetailRepository
import com.jmyentaku.app.viewmodel.detail.state.DetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val repository = DetailRepository()

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun loadDetails(id: Int, type: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            try {
                when (type) {
                    "anime" -> {
                        val animeDetail = repository.getAnimeDetails(id)
                        _uiState.value = _uiState.value.copy(
                            animeDetail = animeDetail,
                            isLoading = false
                        )
                    }
                    "manga" -> {
                        val mangaDetail = repository.getMangaDetails(id)
                        _uiState.value = _uiState.value.copy(
                            mangaDetail = mangaDetail,
                            isLoading = false
                        )
                    }
                    "actor" -> {
                        val voiceActorDetail = repository.getVoiceActorDetails(id)
                        _uiState.value = _uiState.value.copy(
                            voiceActorDetail = voiceActorDetail,
                            isLoading = false
                        )
                    }
                    else -> {
                        _uiState.value = _uiState.value.copy(
                            error = "Unknown type: $type",
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun clearState() {
        _uiState.value = DetailUiState()
    }
}