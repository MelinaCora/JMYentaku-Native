package com.jmyentaku.app.ui.components.GeneralComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
    actor: VoiceActor,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(260.dp)
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column {
            AsyncImage(
                model = actor.images.jpg.image_url,
                contentDescription = actor.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = actor.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "❤️ ${actor.favorites}",
                    fontSize = 12.sp
                )
            }
        }
    }
}