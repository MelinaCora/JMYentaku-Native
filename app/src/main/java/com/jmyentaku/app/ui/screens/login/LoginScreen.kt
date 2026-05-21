package com.jmyentaku.app.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jmyentaku.app.ui.navigation.Routes

@Composable
fun LoginScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Login Screen")

        Button(
            onClick = {
                navController.navigate(Routes.Home.route)
            }
        ) {
            Text(text = "Ir al Home")
        }

        Button(
            onClick = {
                navController.navigate(Routes.Register.route)
            }
        ) {
            Text(text = "Ir a Registro")
        }
    }
}