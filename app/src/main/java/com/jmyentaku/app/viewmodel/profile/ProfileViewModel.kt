package com.jmyentaku.app.viewmodel.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.google.firebase.firestore.ListenerRegistration

import kotlinx.coroutines.launch

import com.jmyentaku.app.data.firebase.AnimeListRepository
import com.jmyentaku.app.data.firebase.ActivityRepository

import com.jmyentaku.app.data.achievements.AchievementEngine
import com.jmyentaku.app.data.challenges.ChallengeEngine
import com.jmyentaku.app.data.streaks.StreakEngine

import com.jmyentaku.app.viewmodel.profile.state.ProfileUiState
import com.jmyentaku.app.data.levels.LevelEngine

class ProfileViewModel : ViewModel() {

    private var listener: ListenerRegistration? = null

    private val repository =
        AnimeListRepository()

    private val activityRepository =
        ActivityRepository()

    var uiState by mutableStateOf(
        ProfileUiState()
    )
        private set

    init {

        observeProfileData()
        loadStreaks()
    }

    private fun observeProfileData() {

        listener = repository.observeUserAnime(

            onUpdate = { animeList ->

                val watching =
                    animeList.count {

                        it.status == "in_progress"
                    }

                val completed =
                    animeList.count {

                        it.status == "completed"
                    }

                val planned =
                    animeList.count {

                        it.status == "planned"
                    }

                val achievements =
                    AchievementEngine.calculateAchievements(

                        completedCount = completed,

                        watchingCount = watching,

                        plannedCount = planned
                    )

                val challenges =
                    ChallengeEngine.calculateChallenges(

                        completedCount = completed,

                        watchingCount = watching,

                        plannedCount = planned
                    )

                val level =

                    LevelEngine.calculateLevel(

                        completedCount = completed,

                        watchingCount = watching,

                        plannedCount = planned,

                        achievementCount = achievements.size,

                        currentStreak = uiState.currentStreak
                    )

                uiState = uiState.copy(

                    watchingCount = watching,

                    completedCount = completed,

                    plannedCount = planned,

                    totalAnime = animeList.size,

                    achievements = achievements,

                    challenges = challenges,

                    level = level.level,

                    currentXp = level.currentXp,

                    xpForNextLevel = level.xpForNextLevel,

                    isLoading = false
                )
            },

            onError = { exception ->

                uiState = uiState.copy(

                    error = exception.message,

                    isLoading = false
                )
            }
        )
    }

    private fun loadStreaks() {

        viewModelScope.launch {

            try {

                val activities =
                    activityRepository.getActivities()

                val streak =
                    StreakEngine.calculateStreak(
                        activities
                    )

                uiState = uiState.copy(

                    currentStreak =
                        streak.currentStreak,

                    longestStreak =
                        streak.longestStreak,

                    activeDays =
                        streak.activeDays
                )

            } catch (_: Exception) {

            }
        }
    }

    override fun onCleared() {

        super.onCleared()

        listener?.remove()
    }
}

