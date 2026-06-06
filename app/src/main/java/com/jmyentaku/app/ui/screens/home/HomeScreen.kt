package com.jmyentaku.app.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.jmyentaku.app.ui.components.GeneralComponent.MainTopBar
import com.jmyentaku.app.ui.components.home.AchievementProgressCard
import com.jmyentaku.app.ui.components.home.ContinueWatchingCard
import com.jmyentaku.app.ui.components.home.DailyGoalsCard
import com.jmyentaku.app.ui.components.home.DashboardCard
import com.jmyentaku.app.ui.navigation.Routes
import com.jmyentaku.app.ui.navigation.passIdAndType
import com.jmyentaku.app.viewmodel.home.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val viewModel: HomeViewModel = viewModel()

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

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
                },
                onProfileClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(Routes.Profile.route)
                },
                onHomeClick = {
                    scope.launch { drawerState.close() }
                },
                onContactClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(Routes.Contact.route)
                },
                onBookmarksClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(Routes.Bookmarks.route)
                }
            )
        }
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Scaffold(
                containerColor = Color.Transparent
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
                        .padding(top = 64.dp)
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
                                        modifier = Modifier.size(60.dp)
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
                                    modifier = Modifier.height(24.dp)
                                )

                                DashboardCard(

                                    streak = viewModel.uiState.currentStreak,

                                    level = viewModel.uiState.level,

                                    xp = viewModel.uiState.currentXp,

                                    nextLevelXp = viewModel.uiState.xpForNextLevel
                                )

                                Spacer(
                                    modifier = Modifier.height(24.dp)
                                )

                                Text(
                                    text = "Continue Watching",
                                    color = Color.White,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(
                                    modifier = Modifier.height(12.dp)
                                )
                                viewModel.uiState.continueWatching
                                    .take(3)
                                    .forEach { anime ->

                                        ContinueWatchingCard(

                                            anime = anime,

                                            onClick = {

                                                navController.navigate(

                                                    passIdAndType(

                                                        anime.animeId,

                                                        anime.type
                                                    )
                                                )
                                            }
                                        )

                                        Spacer(
                                            modifier = Modifier.height(12.dp)
                                        )
                                    }

                                Text(
                                    text = "Next Achievement",
                                    color = Color.White,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(
                                    modifier = Modifier.height(20.dp)
                                )

                                AchievementProgressCard(

                                    achievementName =
                                        viewModel.uiState.nextAchievementName,

                                    current =
                                        viewModel.uiState.nextAchievementProgress,

                                    target =
                                        viewModel.uiState.nextAchievementTarget
                                )
                                Spacer(
                                    modifier = Modifier.height(16.dp)
                                )

                                Text(
                                    text = "Today's Goals",
                                    color = Color.White,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(
                                    modifier = Modifier.height(16.dp)
                                )

                                DailyGoalsCard(

                                    watchedEpisode =
                                        viewModel.uiState.dailyGoalWatchEpisode,

                                    maintainedStreak =
                                        viewModel.uiState.dailyGoalMaintainStreak,

                                    completedAnime =
                                        viewModel.uiState.dailyGoalCompleteAnime
                                )
                            }
                        }
                    }
                }
            }

            MainTopBar(
                title = "JMYentaku",
                onMenuClick = {
                    scope.launch {
                        drawerState.open()
                    }
                },
                onSearchResultClick = { id, type ->
                    navController.navigate(
                        passIdAndType(id, type)
                    )
                }
            )
        }
    }
}