package com.jmyentaku.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jmyentaku.app.ui.screens.details.DetailScreen
import com.jmyentaku.app.ui.screens.home.HomeScreen
import com.jmyentaku.app.ui.screens.login.LoginScreen
import com.jmyentaku.app.ui.screens.register.RegisterScreen
import com.jmyentaku.app.ui.screens.splash.SplashScreen
import com.jmyentaku.app.ui.screens.profile.ProfileScreen
import com.jmyentaku.app.ui.screens.bookmarks.BookmarksScreen
import com.jmyentaku.app.ui.screens.contact.ContactScreen
import com.jmyentaku.app.ui.screens.scan.ScanMangaScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route
    ) {
        composable(Routes.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(Routes.Login.route) {
            LoginScreen(navController)
        }

        composable(Routes.Register.route) {
            RegisterScreen(navController)
        }
        composable(Routes.Contact.route) {
            ContactScreen(navController = navController)
        }

        composable(Routes.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Routes.Detail.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("type") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val type = backStackEntry.arguments?.getString("type") ?: ""
            DetailScreen(
                navController = navController,
                id = id,
                type = type
            )
        }

        composable(Routes.Profile.route) {
            ProfileScreen(navController)
        }

        composable(
            Routes.Bookmarks.route
        ) {

            BookmarksScreen(navController)
        }

        composable(
            Routes.ScanManga.route
        ) {

            ScanMangaScreen()
        }
    }
}