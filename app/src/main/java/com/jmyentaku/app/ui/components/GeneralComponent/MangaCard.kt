package com.jmyentaku.app.ui.components.GeneralComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jmyentaku.app.data.model.ManualManga

@Composable
fun MangaCard(
    manga: ManualManga,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E293B)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // 🔥 TITULO
            Text(
                text = manga.title,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            // 👤 AUTOR
            Text(
                text = "Author: ${manga.author}",
                color = Color.LightGray
            )

            // 🏷 GENRE
            Text(
                text = "Genre: ${manga.genre}",
                color = Color.LightGray
            )

            // 📚 CHAPTERS TOTALES
            Text(
                text = "Total Chapters: ${manga.chapters}",
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(6.dp))

            // 🔥 TRACKER INFO (NUEVO)
            Text(
                text = "Progress: ${manga.currentChapter} / ${manga.chapters}",
                color = Color(0xFF38BDF8), // celeste moderno
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Status: ${manga.status}",
                color = when (manga.status) {
                    "completed" -> Color(0xFF22C55E) // verde
                    "reading" -> Color(0xFF38BDF8)   // celeste
                    "in_progress" -> Color(0xFFFACC15) // amarillo
                    else -> Color.LightGray
                },
                fontWeight = FontWeight.Medium
            )
        }
    }
}