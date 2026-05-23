package com.jmyentaku.app.viewmodel.home.state

import com.jmyentaku.app.data.model.Anime

data class HomeUiState(

    val animeList: List<Anime> = emptyList(),

    val isLoading: Boolean = false,

    val error: String? = null
)