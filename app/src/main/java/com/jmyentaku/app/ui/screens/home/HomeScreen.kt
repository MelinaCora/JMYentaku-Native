package com.jmyentaku.app.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.jmyentaku.app.ui.components.GeneralComponent.CustomButton
import com.jmyentaku.app.ui.navigation.Routes
import com.jmyentaku.app.viewmodel.home.HomeViewModel
import com.jmyentaku.app.ui.components.GeneralComponent.SectionAnimeRow

@Composable
fun HomeScreen(
    navController: NavController
) {

    val viewModel: HomeViewModel = viewModel()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

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

        if (viewModel.uiState.isLoading) {

            Text(text = "Cargando...")

        } else {

            LazyColumn {

                item {

                    SectionAnimeRow(
                        title = "Top Animes",
                        animes = viewModel.uiState.animeList
                    )

                    SectionAnimeRow(
                        title = "Top Mangas",
                        animes = viewModel.uiState.animeList
                    )

                    SectionAnimeRow(
                        title = "Best Voice Actors",
                        animes = viewModel.uiState.animeList
                    )
                }
            }
        }
    }
}