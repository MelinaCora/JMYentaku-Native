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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.jmyentaku.app.ui.components.GeneralComponent.DrawerContent
import com.jmyentaku.app.ui.components.GeneralComponent.SectionAnimeRow
import com.jmyentaku.app.ui.components.GeneralComponent.VoiceActorCard
import com.jmyentaku.app.ui.navigation.Routes
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
                    FirebaseAuth.getInstance().signOut()
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
                    title = { Text("JMYentaku") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch { drawerState.open() }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú"
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
                                animes = viewModel.uiState.mangas
                            )

                            Text(
                                text = "Best Voice Actors",
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )

                            LazyRow {
                                items(viewModel.uiState.voiceActors) { actor ->
                                    VoiceActorCard(actor = actor)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}