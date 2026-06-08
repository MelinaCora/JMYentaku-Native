package com.jmyentaku.app.ui.screens.scan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

import com.jmyentaku.app.ui.components.GeneralComponent.AnimeCard
import com.jmyentaku.app.ui.components.GeneralComponent.DrawerContent
import com.jmyentaku.app.ui.components.GeneralComponent.MainTopBar
import com.jmyentaku.app.ui.navigation.Routes
import com.jmyentaku.app.viewmodel.scan.ScanViewModel

import kotlinx.coroutines.launch

@Composable
fun OCRResultScreen(
    navController: NavController,
    scanViewModel: ScanViewModel
) {

    val mangas = scanViewModel.foundMangas

    val drawerState =
        rememberDrawerState(
            initialValue = DrawerValue.Closed
        )

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(

        drawerState = drawerState,

        drawerContent = {

            DrawerContent(

                onHomeClick = {

                    scope.launch {
                        drawerState.close()
                    }

                    navController.navigate(
                        Routes.Home.route
                    )
                },

                onLogout = {

                    scope.launch {
                        drawerState.close()
                    }

                    navController.navigate(
                        Routes.Login.route
                    ) {
                        popUpTo(0)
                    }
                },

                onProfileClick = {

                    scope.launch {
                        drawerState.close()
                    }

                    navController.navigate(
                        Routes.Profile.route
                    )
                },

                onContactClick = {

                    scope.launch {
                        drawerState.close()
                    }

                    navController.navigate(
                        Routes.Contact.route
                    )
                },

                onBookmarksClick = {

                    scope.launch {
                        drawerState.close()
                    }

                    navController.navigate(
                        Routes.Bookmarks.route
                    )
                }
            )
        }
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Scaffold(
                containerColor = Color(0xFF0F172A)
            ) { paddingValues ->

                Column(

                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF0F172A))
                        .padding(paddingValues)
                        .padding(top = 80.dp)
                ) {

                    Text(

                        text = "Resultados",

                        color = Color.White,

                        style = MaterialTheme.typography.headlineSmall,

                        modifier = Modifier.padding(
                            horizontal = 16.dp
                        )
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(

                        text = "Encontramos ${mangas.size} coincidencias",

                        color = Color.LightGray,

                        modifier = Modifier.padding(
                            horizontal = 16.dp
                        )
                    )

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    if (mangas.isEmpty()) {

                        Box(

                            modifier = Modifier.fillMaxSize(),

                            contentAlignment = Alignment.Center
                        ) {

                            Text(

                                text = "No se encontraron resultados",

                                color = Color.White
                            )
                        }

                    } else {

                        LazyRow(

                            contentPadding = PaddingValues(
                                horizontal = 16.dp
                            ),

                            horizontalArrangement =
                                Arrangement.spacedBy(12.dp)
                        ) {

                            items(mangas) { manga ->

                                AnimeCard(

                                    anime = manga,

                                    type = "manga",

                                    onClick = {

                                        // Próximo paso:
                                        // navegar a DetailScreen
                                    }
                                )
                            }
                        }
                    }
                }
            }

            MainTopBar(

                title = "OCR Results",

                onMenuClick = {

                    scope.launch {
                        drawerState.open()
                    }
                },

                onSearchResultClick = { id, type ->

                    navController.navigate(
                        "detail/$id/$type"
                    )
                }
            )
        }
    }
}