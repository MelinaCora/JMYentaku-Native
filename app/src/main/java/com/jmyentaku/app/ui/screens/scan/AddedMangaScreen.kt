package com.jmyentaku.app.ui.screens.scan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.runtime.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button


import com.jmyentaku.app.data.model.ManualManga
import com.jmyentaku.app.ui.components.GeneralComponent.MangaCard
import com.jmyentaku.app.viewmodel.added.AddedMangaViewModel
import com.jmyentaku.app.viewmodel.manual.ManualMangaViewModel


@Composable
fun AddedMangaScreen(navController: NavController) {

    val viewModel: ManualMangaViewModel = viewModel()
    val mangas = viewModel.mangas.value

    var selectedManga by remember { mutableStateOf<ManualManga?>(null) }

    LaunchedEffect(Unit) {
        viewModel.loadMangas()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A))
            .padding(16.dp)
    ) {

        Text(
            text = "Added Manga",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(mangas) { manga ->
                MangaCard(
                    manga = manga,
                    onClick = {
                        selectedManga = manga
                    }
                )
            }
        }

        selectedManga?.let { manga ->
            MangaTrackerDialog(
                manga = manga,
                onDismiss = { selectedManga = null },
                onAdd = { viewModel.addChapter(manga) },
                onRemove = { viewModel.removeChapter(manga) },
                onStatusChange = { viewModel.updateStatus(manga, it) }
            )
        }
    }
}


@Composable
fun MangaTrackerDialog(
    manga: ManualManga,
    onDismiss: () -> Unit,
    onAdd: () -> Unit,
    onRemove: () -> Unit,
    onStatusChange: (String) -> Unit
) {

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text(text = manga.title)
        },

        text = {

            Column {

                Text(
                    text = "Status: ${manga.status}",
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Progress: ${manga.currentChapter} / ${manga.chapters}",
                    color = Color(0xFF38BDF8)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Change status:")

                Spacer(modifier = Modifier.height(8.dp))

                Row {

                    Button(onClick = { onStatusChange("reading") }) {
                        Text("Reading")
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Button(onClick = { onStatusChange("in_progress") }) {
                        Text("In Progress")
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Button(onClick = { onStatusChange("completed") }) {
                        Text("Completed")
                    }
                }
            }
        },

        confirmButton = {

            Row {

                Button(onClick = onRemove) {
                    Text("-")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = onAdd) {
                    Text("+")
                }
            }
        }
    )
}