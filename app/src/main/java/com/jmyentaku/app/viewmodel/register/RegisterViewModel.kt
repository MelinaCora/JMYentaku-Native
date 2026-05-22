package com.jmyentaku.app.viewmodel.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.jmyentaku.app.viewmodel.register.state.RegisterUiState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.jmyentaku.app.data.firebase.AuthRepository

class RegisterViewModel : ViewModel() {

    private val repository = AuthRepository()

    var uiState by mutableStateOf(RegisterUiState())
        private set

    fun onUsernameChange(newUsername: String) {

        uiState = uiState.copy(
            username = newUsername
        )
    }

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

    fun onConfirmPasswordChange(newConfirmPassword: String) {

        uiState = uiState.copy(
            confirmPassword = newConfirmPassword
        )
    }

    fun register(
        onSuccess: () -> Unit
    ) {

        if (
            uiState.username.isBlank() ||
            uiState.email.isBlank() ||
            uiState.password.isBlank() ||
            uiState.confirmPassword.isBlank()
        ) {

            uiState = uiState.copy(
                error = "Completa todos los campos"
            )

            return
        }

        if (
            uiState.password != uiState.confirmPassword
        ) {

            uiState = uiState.copy(
                error = "Las contraseñas no coinciden"
            )

            return
        }

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true,
                error = null
            )

            val result = repository.register(
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