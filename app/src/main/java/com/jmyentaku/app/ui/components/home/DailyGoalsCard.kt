package com.jmyentaku.app.ui.components.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DailyGoalsCard(

    watchedEpisode: Boolean,

    maintainedStreak: Boolean,

    completedAnime: Boolean
) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(

            containerColor = Color(0xFF1E293B)
        ),

        shape = RoundedCornerShape(24.dp)
    ) {

        Column(

            modifier = Modifier.padding(20.dp)
        ) {

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            GoalRow(

                completed = watchedEpisode,

                text = "Watch 1 episode"
            )

            GoalRow(

                completed = maintainedStreak,

                text = "Maintain streak"
            )

            GoalRow(

                completed = completedAnime,

                text = "Complete 1 anime"
            )
        }
    }
}

@Composable
private fun GoalRow(

    completed: Boolean,

    text: String
) {

    Row(

        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(

            text =
                if (completed) "✅"
                else "⬜",

            color = Color.White
        )

        Text(

            text = text,

            color = Color.White
        )
    }

    Spacer(
        modifier = Modifier.height(12.dp)
    )
}