package com.jmyentaku.app.ui.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jmyentaku.app.ui.components.GeneralComponent.CustomButton
import com.jmyentaku.app.ui.components.form.CustomTextField
import com.jmyentaku.app.ui.navigation.Routes
import com.jmyentaku.app.viewmodel.register.RegisterViewModel

@Composable
fun RegisterScreen(
    navController: NavController
) {

    val viewModel: RegisterViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Registro")

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            value = viewModel.uiState.username,
            onValueChange = {
                viewModel.onUsernameChange(it)
            },
            label = "Username"
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomTextField(
            value = viewModel.uiState.email,
            onValueChange = {
                viewModel.onEmailChange(it)
            },
            label = "Email"
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomTextField(
            value = viewModel.uiState.password,
            onValueChange = {
                viewModel.onPasswordChange(it)
            },
            label = "Password"
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomTextField(
            value = viewModel.uiState.confirmPassword,
            onValueChange = {
                viewModel.onConfirmPasswordChange(it)
            },
            label = "Confirm Password"
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (viewModel.uiState.isLoading) {

            Text(text = "Cargando...")

        } else {

            CustomButton(
                text = "Registrarse",
                onClick = {

                    viewModel.register {

                        navController.navigate(Routes.Home.route)
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        CustomButton(
            text = "Volver al Login",
            onClick = {
                navController.navigate(Routes.Login.route)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = viewModel.uiState.error ?: ""
        )
    }
}