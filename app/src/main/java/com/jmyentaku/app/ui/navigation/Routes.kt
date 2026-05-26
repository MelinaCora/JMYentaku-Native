package com.jmyentaku.app.ui.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")
    object Home : Routes("home")
    object Detail : Routes("detail/{id}/{type}")
    object Splash : Routes("splash")

    object Profile : Routes("profile")

}
//funcion aux
fun passIdAndType(id: Int, type: String): String {
    return "detail/$id/$type"
}