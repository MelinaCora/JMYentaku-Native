package com.jmyentaku.app.ui.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")
    object Home : Routes("home")
    object Detail : Routes("detail/{id}/{type}")
    object Splash : Routes("splash")
    object Contact : Routes("contact")
    object Profile : Routes("profile")
    object ScanManga : Routes("scan_manga")
    object Bookmarks : Routes("bookmarks")

}
//funcion aux
fun passIdAndType(id: Int, type: String): String {
    return "detail/$id/$type"
}