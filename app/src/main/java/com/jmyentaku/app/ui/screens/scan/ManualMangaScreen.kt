package com.jmyentaku.app.ui.screens.scan

import android.widget.Toast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavController

import com.jmyentaku.app.ui.components.GeneralComponent.DrawerContent
import com.jmyentaku.app.ui.components.GeneralComponent.MainTopBar
import com.jmyentaku.app.ui.components.form.CustomTextField
import com.jmyentaku.app.ui.navigation.Routes

import com.jmyentaku.app.viewmodel.manual.ManualMangaViewModel

import kotlinx.coroutines.launch

@Composable
fun ManualMangaScreen(
    navController: NavController
) {

    val viewModel: ManualMangaViewModel = viewModel()

    val context = LocalContext.current

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
                },

                onAddedMangaClick = {
                    scope.launch {
                        drawerState.close()
                    }

                    navController.navigate(
                        Routes.AddedManga.route
                    )
                },

                onScanMangaClick = {
                    scope.launch {
                        drawerState.close()
                    }

                    navController.navigate(
                        Routes.ScanManga.route
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
                        .padding(16.dp)
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {

                    Text(

                        text = "📚 Add Manual Manga",

                        color = Color.White,

                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(

                        text = "No encontramos coincidencias en Jikan. Agrega el manga manualmente.",

                        color = Color.LightGray
                    )

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    CustomTextField(

                        value = viewModel.title,

                        onValueChange = {

                            viewModel.updateTitle(it)
                        },

                        label = "Title"
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    CustomTextField(

                        value = viewModel.author,

                        onValueChange = {

                            viewModel.updateAuthor(it)
                        },

                        label = "Author"
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    CustomTextField(

                        value = viewModel.genre,

                        onValueChange = {

                            viewModel.updateGenre(it)
                        },

                        label = "Genre"
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    CustomTextField(

                        value = viewModel.chapters,

                        onValueChange = {

                            viewModel.updateChapters(it)
                        },

                        label = "Chapters"
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    CustomTextField(

                        value = viewModel.description,

                        onValueChange = {

                            viewModel.updateDescription(it)
                        },

                        label = "Description"
                    )

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    Button(

                        modifier = Modifier.fillMaxWidth(),

                        onClick = {

                            viewModel.saveManualManga {

                                Toast.makeText(

                                    context,

                                    "Manga guardado correctamente",

                                    Toast.LENGTH_SHORT

                                ).show()

                                navController.navigate(
                                    Routes.Home.route
                                )
                            }
                        }
                    ) {

                        Text(
                            text = "➕ Save Manga"
                        )
                    }
                }
            }

            MainTopBar(

                title = "Manual Manga",

                onMenuClick = {

                    scope.launch {
                        drawerState.open()
                    }
                }
            )
        }
    }
}