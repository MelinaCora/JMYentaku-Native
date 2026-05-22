package com.jmyentaku.app.viewmodel.register.state

data class RegisterUiState(

    val username: String = "",

    val email: String = "",

    val password: String = "",

    val confirmPassword: String = "",

    val isLoading: Boolean = false,

    val error: String? = null
)