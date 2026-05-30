package com.jmyentaku.app.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jmyentaku.app.data.model.Achievement

@Composable
fun AchievementCard(

    achievement: Achievement
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
                    if (achievement.unlocked)
                        "🏆 ${achievement.title}"
                    else
                        "🔒 ${achievement.title}",

                color =
                    if (achievement.unlocked)
                        Color(0xFF38BDF8)
                    else
                        Color.White,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(

                text = achievement.description,

                color = Color.LightGray
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            LinearProgressIndicator(

                progress = {

                    achievement.progress.toFloat() /
                            achievement.target.toFloat()
                },

                color = Color(0xFF38BDF8),

                trackColor = Color(0xFF334155),

                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(

                text =
                    "${achievement.progress}/${achievement.target}",

                color = Color.LightGray
            )
        }
    }
}