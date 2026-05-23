package com.jmyentaku.app.ui.components.GeneralComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jmyentaku.app.data.model.Anime

@Composable
fun AnimeCard(
    anime: Anime
) {

    Column(
        modifier = Modifier.padding(8.dp)
    ) {

        Text(text = anime.title)
    }
}