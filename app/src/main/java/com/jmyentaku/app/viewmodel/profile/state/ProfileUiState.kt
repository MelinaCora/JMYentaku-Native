package com.jmyentaku.app.viewmodel.profile.state

data class ProfileUiState(

    val watchingCount: Int = 0,

    val completedCount: Int = 0,

    val plannedCount: Int = 0,

    val totalAnime: Int = 0,

    val isLoading: Boolean = false,

    val error: String? = null
)