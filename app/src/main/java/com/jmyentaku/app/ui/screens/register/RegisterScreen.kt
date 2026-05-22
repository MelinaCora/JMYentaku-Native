package com.jmyentaku.app.ui.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jmyentaku.app.ui.navigation.Routes

@Composable
fun RegisterScreen(
    navController: NavController
) {

    var username by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Registro")

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
            },
            label = {
                Text(text = "Username")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Password")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
            },
            label = {
                Text(text = "Confirm Password")
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                if (
                    username.isBlank() ||
                    email.isBlank() ||
                    password.isBlank() ||
                    confirmPassword.isBlank()
                ) {

                    errorMessage = "Completa todos los campos"

                    return@Button
                }

                if (password != confirmPassword) {

                    errorMessage = "Las contraseñas no coinciden"

                    return@Button
                }

                errorMessage = ""

                println(username)
                println(email)
                println(password)

                navController.navigate(Routes.Home.route)
            }
        ) {

            Text(text = "Registrarse")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                navController.navigate(Routes.Login.route)
            }
        ) {
            Text(text = "Volver al Login")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = errorMessage)
    }
}