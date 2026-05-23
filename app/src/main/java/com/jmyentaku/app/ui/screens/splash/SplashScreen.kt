package com.jmyentaku.app.ui.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jmyentaku.app.ui.navigation.Routes
import com.jmyentaku.app.viewmodel.splash.SplashViewModel

@Composable
fun SplashScreen(
    navController: NavController
) {

    val viewModel: SplashViewModel = viewModel()

    LaunchedEffect(Unit) {

        if (viewModel.isUserLogged()) {

            navController.navigate(
                Routes.Home.route
            )

        } else {

            navController.navigate(
                Routes.Login.route
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "JMyentaku")
    }
}

