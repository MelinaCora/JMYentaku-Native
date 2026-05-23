package com.jmyentaku.app.viewmodel.home.state

import com.jmyentaku.app.data.model.Anime
import com.jmyentaku.app.data.model.VoiceActor

data class HomeUiState(

    val animeList: List<Anime> = emptyList(),

    val mangas: List<Anime> = emptyList(),

    val voiceActors: List<VoiceActor> = emptyList(),

    val isLoading: Boolean = false,

    val error: String? = null
)