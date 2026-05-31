package com.jmyentaku.app.viewmodel.search.state

import com.jmyentaku.app.data.model.Anime
import com.jmyentaku.app.data.model.VoiceActor

data class SearchUiState(
    val animes: List<Anime> = emptyList(),
    val mangas: List<Anime> = emptyList(),
    val voiceActors: List<VoiceActor> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)