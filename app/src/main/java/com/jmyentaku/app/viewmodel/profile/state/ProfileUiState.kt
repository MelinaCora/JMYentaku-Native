package com.jmyentaku.app.viewmodel.profile.state

import com.jmyentaku.app.data.model.Achievement
import com.jmyentaku.app.data.model.Challenge

data class ProfileUiState(

    val watchingCount: Int = 0,

    val completedCount: Int = 0,

    val plannedCount: Int = 0,

    val totalAnime: Int = 0,

    val currentStreak: Int = 0,

    val longestStreak: Int = 0,

    val activeDays: Int = 0,

    val isLoading: Boolean = false,

    val error: String? = null,

    val achievements: List<Achievement> = emptyList(),

    val challenges: List<Challenge> = emptyList()
)