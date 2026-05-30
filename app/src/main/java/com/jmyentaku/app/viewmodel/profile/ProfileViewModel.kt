package com.jmyentaku.app.viewmodel.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmyentaku.app.data.firebase.AnimeListRepository
import com.jmyentaku.app.viewmodel.profile.state.ProfileUiState
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val repository =
        AnimeListRepository()

    var uiState by mutableStateOf(
        ProfileUiState()
    )
        private set

    init {

        loadProfileData()
    }

    private fun loadProfileData() {

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true
            )

            repository.getAllUserAnime()

                .onSuccess { animeList ->

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

                    uiState = uiState.copy(

                        watchingCount = watching,

                        completedCount = completed,

                        plannedCount = planned,

                        totalAnime = animeList.size,

                        isLoading = false
                    )
                }

                .onFailure { exception ->

                    uiState = uiState.copy(

                        error = exception.message,

                        isLoading = false
                    )
                }
        }
    }
}