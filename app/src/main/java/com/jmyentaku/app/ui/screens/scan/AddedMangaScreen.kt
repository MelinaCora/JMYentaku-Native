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
import androidx.compose.foundation.clickable


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
        containerColor = Color(0xFF0F172A), // fondo oscuro moderno

        title = {
            Text(
                text = manga.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },

        text = {

            Column {

                // 📊 PROGRESS CARD
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(0xFF1E293B),
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
                        )
                        .padding(12.dp)
                ) {

                    Column {

                        Text(
                            text = "Progress",
                            color = Color.LightGray
                        )

                        Text(
                            text = "${manga.currentChapter} / ${manga.chapters}",
                            color = Color(0xFF38BDF8),
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Status: ${manga.status}",
                            color = when (manga.status) {
                                "completed" -> Color(0xFF22C55E)
                                "reading" -> Color(0xFF38BDF8)
                                "in_progress" -> Color(0xFFFACC15)
                                else -> Color.Gray
                            },
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Change status",
                    color = Color.LightGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 🎯 CHIPS DE ESTADO
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    StatusChip(
                        text = "Reading",
                        color = Color(0xFF38BDF8),
                        onClick = { onStatusChange("reading") }
                    )

                    StatusChip(
                        text = "In Progress",
                        color = Color(0xFFFACC15),
                        onClick = { onStatusChange("in_progress") }
                    )

                    StatusChip(
                        text = "Completed",
                        color = Color(0xFF22C55E),
                        onClick = { onStatusChange("completed") }
                    )
                }
            }
        },

        confirmButton = {

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                ActionButton("-", onRemove)

                ActionButton("+", onAdd)
            }
        }
    )
}

@Composable
fun StatusChip(
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color.copy(alpha = 0.15f),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = color,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                Color(0xFF38BDF8),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 18.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}