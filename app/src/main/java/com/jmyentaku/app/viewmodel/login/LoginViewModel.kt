package com.jmyentaku.app.viewmodel.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jmyentaku.app.viewmodel.login.state.LoginUiState
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

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
                error = "Completa todos los campos"
            )

            return
        }

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true,
                error = null
            )

            delay(3000)

            println(uiState.email)
            println(uiState.password)

            uiState = uiState.copy(
                isLoading = false
            )

            onSuccess()
        }
    }
}
