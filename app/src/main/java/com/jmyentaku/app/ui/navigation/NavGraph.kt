package com.jmyentaku.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jmyentaku.app.ui.screens.details.DetailScreen
import com.jmyentaku.app.ui.screens.home.HomeScreen
import com.jmyentaku.app.ui.screens.login.LoginScreen
import com.jmyentaku.app.ui.screens.register.RegisterScreen
import com.jmyentaku.app.ui.screens.splash.SplashScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route
    ) {

        composable(Routes.Login.route) {
            LoginScreen(navController)
        }

        composable(Routes.Register.route) {
            RegisterScreen(navController)
        }

        composable(Routes.Home.route) {
            HomeScreen()
        }

        composable(Routes.Detail.route) {
            DetailScreen()
        }

        composable(
            route = Routes.Splash.route
        ) {

            SplashScreen(
                navController = navController
            )
        }
    }
}