package com.jmyentaku.app.viewmodel.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jmyentaku.app.data.firebase.AnimeListRepository
import com.jmyentaku.app.viewmodel.profile.state.ProfileUiState
import com.google.firebase.firestore.ListenerRegistration
import com.jmyentaku.app.data.achievements.AchievementEngine

class ProfileViewModel : ViewModel() {

    private var listener: ListenerRegistration? = null

    private val repository =
        AnimeListRepository()

    var uiState by mutableStateOf(
        ProfileUiState()
    )
        private set

    init {

        observeProfileData()
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

                uiState = uiState.copy(

                    watchingCount = watching,

                    completedCount = completed,

                    plannedCount = planned,

                    totalAnime = animeList.size,

                    achievements = achievements,

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

    override fun onCleared() {

        super.onCleared()

        listener?.remove()
    }
}

