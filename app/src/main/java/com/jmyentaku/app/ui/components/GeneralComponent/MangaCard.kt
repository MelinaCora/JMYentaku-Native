package com.jmyentaku.app.ui.components.GeneralComponent

import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = manga.title,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Text(text = "Author: ${manga.author}", color = Color.LightGray)
            Text(text = "Genre: ${manga.genre}", color = Color.LightGray)
            Text(text = "Chapters: ${manga.chapters}", color = Color.LightGray)
        }
    }
}