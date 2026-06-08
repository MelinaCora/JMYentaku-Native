package com.jmyentaku.app.ui.screens.scan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
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

import com.jmyentaku.app.ui.components.GeneralComponent.MangaCard
import com.jmyentaku.app.viewmodel.added.AddedMangaViewModel


@Composable
fun AddedMangaScreen(
    navController: NavController
) {

    val viewModel: AddedMangaViewModel = viewModel()
    val mangas = viewModel.mangas.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A))
            .padding(16.dp)
    ) {

        Text(
            text = "📚 Added Manga",
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
                        // futuro popup progreso
                    }
                )
            }
        }
    }
}