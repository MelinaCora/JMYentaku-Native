package com.jmyentaku.app.ui.components.GeneralComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

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

import com.jmyentaku.app.data.model.VoiceActor

@Composable
fun VoiceActorCard(
    actor: VoiceActor
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
                model = actor.images.jpg.image_url,

                contentDescription = actor.name,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),

                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(12.dp)
            ) {

                Text(
                    text = actor.name,

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
                        text = "❤️ ${actor.favorites}"
                    )
                }
            }
        }
    }
}