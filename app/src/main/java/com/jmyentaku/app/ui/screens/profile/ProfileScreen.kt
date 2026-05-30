package com.jmyentaku.app.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jmyentaku.app.ui.components.profile.ChallengeCard
import com.jmyentaku.app.ui.components.profile.ProfileHeader
import com.jmyentaku.app.ui.components.profile.ProfileStatCard
import com.jmyentaku.app.ui.components.profile.UserListCard
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import com.jmyentaku.app.ui.components.GeneralComponent.DrawerContent
import com.jmyentaku.app.ui.components.GeneralComponent.MainTopBar
import com.jmyentaku.app.ui.navigation.Routes
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.padding
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jmyentaku.app.viewmodel.profile.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavController
) {

    val viewModel: ProfileViewModel = viewModel()

    val uiState = viewModel.uiState

    val drawerState =
        rememberDrawerState(

            initialValue = DrawerValue.Closed
        )

    val scope =
        rememberCoroutineScope()

    val challenges = listOf(

        "Watch 5 anime episodes",

        "Read 10 manga chapters",

        "Maintain your streak"
    )

    val lists = listOf(

        "Watching",

        "Completed",

        "Plan To Watch",

        "Dropped"
    )

    ModalNavigationDrawer(

        drawerState = drawerState,

        drawerContent = {

            DrawerContent(

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
                },
                onContactClick = {

                    scope.launch {
                        drawerState.close()
                    }

                    navController.navigate(Routes.Contact.route)
                },

                onBookmarksClick = {

                    scope.launch {

                        drawerState.close()
                    }

                    navController.navigate(
                        Routes.Bookmarks.route
                    )
                },

                onHomeClick = {

                    scope.launch {

                        drawerState.close()
                    }

                    navController.navigate(
                        Routes.Home.route
                    )
                }
            )
        }
    ) {

        Scaffold(

            containerColor = Color.Transparent,

            topBar = {

                MainTopBar(

                    title = "Profile",

                    onMenuClick = {

                        scope.launch {

                            drawerState.open()
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

                LazyColumn(

                    contentPadding = PaddingValues(16.dp),

                    verticalArrangement =
                        Arrangement.spacedBy(10.dp)
                ) {

                    // HEADER
                    item {

                        ProfileHeader()
                    }

                    // STATS
                    item {

                        Text(

                            text = "Your Stats",

                            color = Color.White,

                            fontSize = 22.sp,

                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Column(

                            verticalArrangement =
                                Arrangement.spacedBy(10.dp)
                        ) {

                            Row(

                                modifier =
                                    Modifier.fillMaxWidth(),

                                horizontalArrangement =
                                    Arrangement.spacedBy(12.dp)
                            ) {

                                ProfileStatCard(
                                    title = "Watching",
                                    value = uiState.watchingCount.toString(),
                                    emoji = "🎬",
                                    modifier = Modifier.weight(1f)
                                )

                                ProfileStatCard(
                                    title = "Completed",
                                    value = uiState.completedCount.toString(),
                                    emoji = "✅",
                                    modifier = Modifier.weight(1f)
                                )
                            }

                            Row(

                                modifier =
                                    Modifier.fillMaxWidth(),

                                horizontalArrangement =
                                    Arrangement.spacedBy(12.dp)
                            ) {

                                ProfileStatCard(
                                    title = "Planned",
                                    value = uiState.plannedCount.toString(),
                                    emoji = "📅",
                                    modifier = Modifier.weight(1f)
                                )

                                ProfileStatCard(
                                    title = "Hours",
                                    value = uiState.totalAnime.toString(),
                                    emoji = "⏳",
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }

                    // CHALLENGES
                    item {

                        Text(

                            text = "Daily Challenges",

                            color = Color.White,

                            fontSize = 22.sp,

                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )
                    }

                    items(challenges) { challenge ->

                        ChallengeCard(
                            title = challenge
                        )
                    }

                    // LISTS
                    item {

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        Text(

                            text = "My Lists",

                            color = Color.White,

                            fontSize = 22.sp,

                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )
                    }

                    items(lists) { list ->

                        UserListCard(
                            title = list
                        )
                    }

                    // ACHIEVEMENTS
                    item {

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        Text(

                            text = "Achievements",

                            color = Color.White,

                            fontSize = 22.sp,

                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(14.dp)
                        )
                    }

                    item {

                        Text(

                            text = "🏆 Otaku Beginner   ⭐ Manga Master   🔥 Night Watcher",

                            color = Color.LightGray,

                            lineHeight = 28.sp
                        )
                    }
                }
            }
        }
    }
}