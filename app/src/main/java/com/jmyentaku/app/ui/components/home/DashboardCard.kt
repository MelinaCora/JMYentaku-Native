package com.jmyentaku.app.ui.components.home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.foundation.layout.*

@Composable
fun DashboardCard(

    streak: Int,

    level: Int,

    xp: Int,

    nextLevelXp: Int
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

            Text(

                text = "🔥 $streak Day Streak",

                color = Color.White,

                fontSize = 22.sp,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(

                text = "⭐ Level $level",

                color = Color(0xFF38BDF8),

                fontSize = 18.sp
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            LinearProgressIndicator(

                progress = {

                    xp.toFloat() / nextLevelXp
                },

                modifier = Modifier.fillMaxWidth(),

                color = Color(0xFF38BDF8),

                trackColor = Color(0xFF334155)
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(

                text = "$xp / $nextLevelXp XP",

                color = Color.LightGray
            )
        }
    }
}