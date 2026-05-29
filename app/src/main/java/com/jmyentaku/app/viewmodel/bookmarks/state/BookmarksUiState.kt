package com.jmyentaku.app.viewmodel.bookmarks.state

import com.jmyentaku.app.data.model.UserAnime

data class BookmarksUiState(

    val animeList: List<UserAnime> = emptyList(),

    val selectedStatus: String = "in_progress",

    val isLoading: Boolean = false,

    val error: String? = null
)