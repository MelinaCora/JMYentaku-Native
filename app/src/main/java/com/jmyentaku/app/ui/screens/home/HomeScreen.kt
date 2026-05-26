package com.jmyentaku.app.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jmyentaku.app.ui.components.GeneralComponent.DrawerContent
import com.jmyentaku.app.ui.components.GeneralComponent.SectionAnimeRow
import com.jmyentaku.app.ui.components.GeneralComponent.VoiceActorCard
import com.jmyentaku.app.ui.navigation.Routes
import com.jmyentaku.app.ui.navigation.passIdAndType
import com.jmyentaku.app.viewmodel.home.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    val viewModel: HomeViewModel = viewModel()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onLogout = {
                    scope.launch { drawerState.close() }
                    viewModel.logout()
                    navController.navigate(Routes.Login.route) {
                        popUpTo(0)
                    }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("JMYEntaku") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch { drawerState.open() }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (viewModel.uiState.isLoading) {
                    Text(text = "Loading...", modifier = Modifier.padding(16.dp))
                } else {
                    LazyColumn {
                        item {
                            SectionAnimeRow(
                                title = "Top Animes",
                                animes = viewModel.uiState.animeList,
                                onAnimeClick = { anime ->
                                    navController.navigate(passIdAndType(anime.mal_id, "anime"))
                                }
                            )

                            SectionAnimeRow(
                                title = "Top Mangas",
                                animes = viewModel.uiState.mangas,
                                onAnimeClick = { manga ->
                                    navController.navigate(passIdAndType(manga.mal_id, "manga"))
                                }
                            )

                            Text(
                                text = "Best Voice Actors",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(16.dp)
                            )

                            LazyRow {
                                items(viewModel.uiState.voiceActors) { actor ->
                                    VoiceActorCard(
                                        actor = actor,
                                        onClick = {
                                            navController.navigate(passIdAndType(actor.mal_id, "actor"))
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}