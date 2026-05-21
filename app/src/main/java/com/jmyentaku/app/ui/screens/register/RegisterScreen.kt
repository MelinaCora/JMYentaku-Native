package com.jmyentaku.app.ui.screens.register

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
fun RegisterScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Register Screen")

        Button(
            onClick = {
                navController.navigate(Routes.Login.route)
            }
        ) {
            Text(text = "Volver al Login")
        }
    }
}