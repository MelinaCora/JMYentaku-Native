package com.jmyentaku.app.ui.navigation
sealed class Routes(val route: String) {

    object Login : Routes("login")
    object Register : Routes("register")
    object Home : Routes("home")
    object Detail : Routes("detail")
    object Splash : Routes("splash")
}