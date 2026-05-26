package com.jmyentaku.app.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jmyentaku.app.R
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

    val drawerState =
        rememberDrawerState(
            initialValue = DrawerValue.Closed
        )

    val scope =
        rememberCoroutineScope()

    ModalNavigationDrawer(

        drawerState = drawerState,

        drawerContent = {

            DrawerContent(

                onLogout = {

                    scope.launch {

                        drawerState.close()
                    }

                    viewModel.logout()

                    navController.navigate(
                        Routes.Login.route
                    ) {

                        popUpTo(0)
                    }
                }
            )
        }
    ) {

        Scaffold(

            containerColor = Color.Transparent,

            topBar = {

                TopAppBar(

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = Color.White
                    ),

                    title = {

                        Text(
                            text = "JMYEntaku",
                            fontWeight = FontWeight.Bold
                        )
                    },

                    navigationIcon = {

                        IconButton(

                            onClick = {

                                scope.launch {

                                    drawerState.open()
                                }
                            }
                        ) {

                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }
                    }
                )
            }

        ) { paddingValues ->

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .background(

                        brush = Brush.verticalGradient(

                            colors = listOf(

                                Color(0xFF0F172A),
                                Color(0xFF111827),
                                Color(0xFF1E1B4B)
                            )
                        )
                    )
                    .padding(paddingValues)
            ) {

                if (viewModel.uiState.isLoading) {

                    Text(
                        text = "Loading...",
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )

                } else {

                    LazyColumn {

                        item {

                            Spacer(
                                modifier = Modifier.height(16.dp)
                            )

                            // HEADER
                            Row(

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp),

                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Image(

                                    painter = painterResource(
                                        id = R.drawable.bonsai_logo
                                    ),

                                    contentDescription = "Logo",

                                    modifier = Modifier
                                        .size(60.dp)
                                )

                                Spacer(
                                    modifier = Modifier.width(16.dp)
                                )

                                Column {

                                    Text(
                                        text = "Welcome back 👋",
                                        color = Color.LightGray,
                                        fontSize = 14.sp
                                    )

                                    Text(
                                        text = "Explore your anime world",
                                        color = Color.White,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(
                                modifier = Modifier.height(28.dp)
                            )

                            // TOP ANIMES
                            SectionAnimeRow(

                                title = "🔥 Top Animes",

                                animes = viewModel.uiState.animeList,

                                onAnimeClick = { anime ->

                                    navController.navigate(

                                        passIdAndType(
                                            anime.mal_id,
                                            "anime"
                                        )
                                    )
                                }
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            // TOP MANGAS
                            SectionAnimeRow(

                                title = "📚 Top Mangas",

                                animes = viewModel.uiState.mangas,

                                onAnimeClick = { manga ->

                                    navController.navigate(

                                        passIdAndType(
                                            manga.mal_id,
                                            "manga"
                                        )
                                    )
                                }
                            )

                            Spacer(
                                modifier = Modifier.height(20.dp)
                            )

                            // VOICE ACTORS TITLE
                            Text(

                                text = "🎙 Best Voice Actors",

                                color = Color.White,

                                fontSize = 24.sp,

                                fontWeight = FontWeight.Bold,

                                modifier = Modifier.padding(
                                    horizontal = 16.dp
                                )
                            )

                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )

                            // VOICE ACTORS ROW
                            LazyRow {

                                items(
                                    viewModel.uiState.voiceActors
                                ) { actor ->

                                    VoiceActorCard(

                                        actor = actor,

                                        onClick = {

                                            navController.navigate(

                                                passIdAndType(
                                                    actor.mal_id,
                                                    "actor"
                                                )
                                            )
                                        }
                                    )
                                }
                            }

                            Spacer(
                                modifier = Modifier.height(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}