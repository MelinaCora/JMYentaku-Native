package com.jmyentaku.app.viewmodel.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jmyentaku.app.viewmodel.login.state.LoginUiState
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.jmyentaku.app.data.firebase.AuthRepository

class LoginViewModel : ViewModel() {

    private val repository = AuthRepository()

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(newEmail: String) {

        uiState = uiState.copy(
            email = newEmail
        )
    }

    fun onPasswordChange(newPassword: String) {

        uiState = uiState.copy(
            password = newPassword
        )
    }

    fun login(
        onSuccess: () -> Unit
    ) {

        if (
            uiState.email.isBlank() ||
            uiState.password.isBlank()
        ) {

            uiState = uiState.copy(
                error = "Complete all fields"
            )

            return
        }

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true,
                error = null
            )

            val result = repository.login(
                email = uiState.email,
                password = uiState.password
            )

            uiState = uiState.copy(
                isLoading = false
            )

            result.onSuccess {

                onSuccess()
            }

            result.onFailure {

                uiState = uiState.copy(
                    error = it.message
                )
            }
        }
    }
}
