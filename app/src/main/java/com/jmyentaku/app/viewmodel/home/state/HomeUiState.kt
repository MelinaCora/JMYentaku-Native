package com.jmyentaku.app.viewmodel.home.state

import com.jmyentaku.app.data.model.Anime
import com.jmyentaku.app.data.model.VoiceActor
import com.jmyentaku.app.data.model.UserAnime

data class HomeUiState(

    val animeList: List<Anime> = emptyList(),
    val mangas: List<Anime> = emptyList(),
    val voiceActors: List<VoiceActor> = emptyList(),

    // Dashboard
    val currentStreak: Int = 0,

    val level: Int = 1,

    val currentXp: Int = 0,

    val xpForNextLevel: Int = 1000,

    val nextAchievementName: String = "",

    val nextAchievementProgress: Int = 0,

    val nextAchievementTarget: Int = 0,

    val dailyGoalWatchEpisode: Boolean = false,

    val dailyGoalMaintainStreak: Boolean = false,

    val dailyGoalCompleteAnime: Boolean = false,

    val continueWatching: List<UserAnime> = emptyList(),

    val isLoading: Boolean = false,

    val error: String? = null
)