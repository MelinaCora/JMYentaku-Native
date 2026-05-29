package com.jmyentaku.app.ui.screens.bookmarks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jmyentaku.app.viewmodel.bookmarks.BookmarksViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp

@Composable
fun BookmarksScreen() {

    val viewModel: BookmarksViewModel = viewModel()

    val uiState = viewModel.uiState

    val tabs = listOf(

        "in_progress",
        "completed",
        "planned"
    )

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFF0F172A)
            )
    ) {

        TabRow(

            selectedTabIndex = tabs.indexOf(
                uiState.selectedStatus
            ),

            containerColor = Color(0xFF111827),

            contentColor = Color(0xFF38BDF8),

            divider = { },

            indicator = { tabPositions ->

                Box(

                    modifier = Modifier
                        .tabIndicatorOffset(

                            tabPositions[
                                tabs.indexOf(
                                    uiState.selectedStatus
                                )
                            ]
                        )
                        .height(3.dp)
                        .background(
                            Color(0xFF38BDF8)
                        )
                )
            }
        ) {

            tabs.forEach { status ->

                Tab(

                    selected = uiState.selectedStatus == status,

                    selectedContentColor = Color(0xFF38BDF8),

                    unselectedContentColor = Color.LightGray,

                    onClick = {

                        viewModel.getAnimeByStatus(
                            status
                        )
                    },

                    text = {

                        Text(

                            text = status
                                .replace("_", " ")
                                .replaceFirstChar {

                                    it.uppercase()
                                }
                        )
                    }
                )
            }
        }

        LazyColumn {

            items(
                uiState.animeList
            ) { anime ->

                Text(
                    text = anime.title,
                    color = Color.White
                )
            }
        }
    }
}