package com.jmyentaku.app.viewmodel.profile.state

import com.jmyentaku.app.data.model.Achievement
import com.jmyentaku.app.data.model.Challenge

data class ProfileUiState(

    val username: String = "Otaku",

    val title: String = "Anime Explorer",

    val watchingCount: Int = 0,

    val completedCount: Int = 0,

    val plannedCount: Int = 0,

    val totalAnime: Int = 0,

    // =========================
    // STREAKS
    // =========================

    val currentStreak: Int = 0,

    val longestStreak: Int = 0,

    val activeDays: Int = 0,

    // =========================
    // LEVEL SYSTEM
    // =========================

    val level: Int = 1,

    val currentXp: Int = 0,

    val xpForNextLevel: Int = 1000,

    // =========================
    // GENERAL
    // =========================

    val isLoading: Boolean = false,

    val error: String? = null,

    // =========================
    // GAMIFICATION
    // =========================

    val achievements: List<Achievement> = emptyList(),

    val challenges: List<Challenge> = emptyList()
)