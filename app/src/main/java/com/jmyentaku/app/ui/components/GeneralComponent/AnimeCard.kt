package com.jmyentaku.app.ui.components.GeneralComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.compose.AsyncImage

import com.jmyentaku.app.data.model.Anime

@Composable
fun AnimeCard(
    anime: Anime
) {

    Card(
        modifier = Modifier
            .width(220.dp)
            .height(420.dp)
            .padding(8.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Column {

            AsyncImage(
                model = anime.images.jpg.image_url,

                contentDescription = anime.title,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),

                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(12.dp)
            ) {

                Text(
                    text = anime.title,

                    fontSize = 18.sp,

                    fontWeight = FontWeight.Bold,

                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,

                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "⭐ ${anime.score}"
                    )

                    Text(
                        text = "❤️ ${anime.favorites}"
                    )
                }
            }
        }
    }
}