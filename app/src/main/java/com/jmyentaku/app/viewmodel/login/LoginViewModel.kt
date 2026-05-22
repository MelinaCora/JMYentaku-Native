package com.jmyentaku.app.viewmodel.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jmyentaku.app.viewmodel.login.state.LoginUiState

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

    fun login() {

        println(uiState.email)
        println(uiState.password)
    }
}