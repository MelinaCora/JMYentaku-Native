package com.jmyentaku.app.ui.components.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jmyentaku.app.data.model.Challenge

@Composable
fun ChallengeProgressCard(

    challenge: Challenge
) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(

            containerColor = Color(0xFF1E293B)
        )
    ) {

        Column(

            modifier = Modifier.padding(16.dp)
        ) {

            Text(

                text =
                    if (challenge.completed)
                        "✅ ${challenge.title}"
                    else
                        "🎯 ${challenge.title}",

                color =
                    if (challenge.completed)
                        Color(0xFF38BDF8)
                    else
                        Color.White,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(

                text = challenge.description,

                color = Color.LightGray
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            LinearProgressIndicator(

                progress = {

                    challenge.progress.toFloat() /
                            challenge.target.toFloat()
                },

                color = Color(0xFF38BDF8),

                trackColor = Color(0xFF334155),

                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(

                text =
                    "${challenge.progress}/${challenge.target}",

                color = Color.LightGray
            )
        }
    }
}