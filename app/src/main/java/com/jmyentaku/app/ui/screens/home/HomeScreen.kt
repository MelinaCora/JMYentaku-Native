package com.jmyentaku.app.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.jmyentaku.app.ui.components.GeneralComponent.CustomButton
import com.jmyentaku.app.ui.navigation.Routes

@Composable
fun HomeScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier.fillMaxSize(),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Home Screen")

        CustomButton(
            text = "Cerrar sesión",
            onClick = {

                FirebaseAuth
                    .getInstance()
                    .signOut()

                navController.navigate(
                    Routes.Login.route
                ) {

                    popUpTo(0)
                }
            }
        )
    }
}