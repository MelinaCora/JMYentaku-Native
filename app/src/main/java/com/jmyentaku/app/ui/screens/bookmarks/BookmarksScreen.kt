package com.jmyentaku.app.ui.screens.bookmarks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.jmyentaku.app.ui.components.GeneralComponent.DrawerContent
import com.jmyentaku.app.ui.components.GeneralComponent.MainTopBar
import com.jmyentaku.app.ui.navigation.Routes
import com.jmyentaku.app.ui.navigation.passIdAndType
import com.jmyentaku.app.viewmodel.bookmarks.BookmarksViewModel
import kotlinx.coroutines.launch

@Composable
fun BookmarksScreen(navController: NavController) {

    val viewModel: BookmarksViewModel = viewModel()
    val uiState = viewModel.uiState

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val tabs = listOf("in_progress", "completed", "planned")

    val tabDisplayNames = mapOf(
        "in_progress" to "In Progress",
        "completed" to "Completed",
        "planned" to "Planned"
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onHomeClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(Routes.Home.route)
                },
                onLogout = {
                    scope.launch { drawerState.close() }
                    navController.navigate(Routes.Login.route) { popUpTo(0) }
                },
                onProfileClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(Routes.Profile.route)
                },
                onContactClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(Routes.Contact.route)
                },
                onBookmarksClick = {
                    scope.launch { drawerState.close() }
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

        Box(modifier = Modifier.fillMaxSize()) {

            Scaffold(
                containerColor = Color(0xFF0F172A)
            ) { paddingValues ->

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF0F172A))
                        .padding(paddingValues)
                        .padding(top = 64.dp)
                ) {

                    TabRow(
                        selectedTabIndex = tabs.indexOf(uiState.selectedStatus),
                        containerColor = Color(0xFF111827),
                        contentColor = Color(0xFF38BDF8),
                        divider = {},
                        indicator = { tabPositions ->
                            Box(
                                modifier = Modifier
                                    .tabIndicatorOffset(tabPositions[tabs.indexOf(uiState.selectedStatus)])
                                    .height(3.dp)
                                    .background(Color(0xFF38BDF8))
                            )
                        }
                    ) {

                        tabs.forEach { status ->

                            Tab(
                                selected = uiState.selectedStatus == status,
                                selectedContentColor = Color(0xFF38BDF8),
                                unselectedContentColor = Color.Gray,
                                onClick = { viewModel.getAnimeByStatus(status) },
                                text = {
                                    Text(
                                        text = tabDisplayNames[status] ?: status,
                                        fontSize = 13.sp,
                                        fontWeight =
                                            if (uiState.selectedStatus == status)
                                                FontWeight.Bold
                                            else
                                                FontWeight.Normal
                                    )
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        items(uiState.animeList.chunked(2)) { rowItems ->

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {

                                rowItems.forEach { item ->

                                    Card(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clickable {
                                                navController.navigate(
                                                    passIdAndType(
                                                        item.animeId,
                                                        item.type
                                                    )
                                                )
                                            },
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color(0xFF1E293B)
                                        ),
                                        shape = RoundedCornerShape(16.dp)
                                    ) {

                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(12.dp)
                                        ) {

                                            AsyncImage(
                                                model = item.imageUrl,
                                                contentDescription = item.title,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(160.dp)
                                                    .clip(RoundedCornerShape(12.dp)),
                                                contentScale = ContentScale.Crop
                                            )

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(
                                                text = item.title,
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 14.sp,
                                                maxLines = 2,
                                                overflow = TextOverflow.Ellipsis
                                            )

                                            Spacer(modifier = Modifier.height(6.dp))

                                            Text(
                                                text = "${item.progress}/${item.total}",
                                                color = Color(0xFF38BDF8),
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold
                                            )

                                            Spacer(modifier = Modifier.height(6.dp))

                                            LinearProgressIndicator(
                                                progress =
                                                    if (item.total > 0)
                                                        item.progress.toFloat() / item.total.toFloat()
                                                    else 0f,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(6.dp),
                                                color = Color(0xFF38BDF8),
                                                trackColor = Color(0xFF334155)
                                            )
                                        }
                                    }
                                }

                                if (rowItems.size == 1) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }

            MainTopBar(
                title = "My Library",
                onMenuClick = {
                    scope.launch { drawerState.open() }
                },
                onSearchResultClick = { id, type ->
                    navController.navigate(passIdAndType(id, type))
                }
            )
        }
    }
}