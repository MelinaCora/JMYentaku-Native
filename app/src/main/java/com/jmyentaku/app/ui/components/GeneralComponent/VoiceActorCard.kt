package com.jmyentaku.app.ui.components.GeneralComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jmyentaku.app.data.model.VoiceActor

@Composable
fun VoiceActorCard(

    actor: VoiceActor
) {

    Card(

        modifier = Modifier
            .width(160.dp)
            .padding(8.dp)
    ) {

        Column(

            verticalArrangement = Arrangement.Center
        ) {

            AsyncImage(

                model = actor.images.jpg.image_url,

                contentDescription = actor.name
            )

            Text(

                text = actor.name,

                modifier = Modifier.padding(8.dp)
            )

            Text(

                text = "❤️ ${actor.favorites}",

                modifier = Modifier.padding(8.dp)
            )
        }
    }
}