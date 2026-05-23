package com.jmyentaku.app.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.jmyentaku.app.ui.components.GeneralComponent.CustomButton
import com.jmyentaku.app.ui.navigation.Routes
import com.jmyentaku.app.viewmodel.home.HomeViewModel
import com.jmyentaku.app.ui.components.GeneralComponent.AnimeCard
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

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

            LazyColumn(
                modifier = Modifier.padding(vertical = 16.dp)
            ) {

                item {

                    Text(
                        text = "Top Animes",

                        fontSize = 24.sp,

                        fontWeight = FontWeight.Bold,

                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    LazyRow {

                        items(viewModel.uiState.animeList) { anime ->

                            AnimeCard(anime = anime)
                        }
                    }
                }
            }
        }
    }
}