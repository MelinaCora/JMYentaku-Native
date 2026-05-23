package com.jmyentaku.app.ui.components.GeneralComponent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmyentaku.app.data.model.Anime

@Composable
fun SectionAnimeRow(
    title: String,
    animes: List<Anime>
) {

    Text(
        text = title,

        fontSize = 24.sp,

        fontWeight = FontWeight.Bold,

        modifier = Modifier.padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(12.dp))

    LazyRow {

        items(animes) { anime ->

            AnimeCard(anime = anime)
        }
    }

    Spacer(modifier = Modifier.height(24.dp))
}