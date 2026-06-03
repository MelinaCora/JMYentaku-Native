package com.jmyentaku.app.ui.components.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AchievementProgressCard(

    achievementName: String,

    current: Int,

    target: Int
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

                text = "🎯 Next Achievement",

                color = Color.White,

                fontSize = 18.sp,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(

                text = achievementName,

                color = Color(0xFF38BDF8),

                fontSize = 22.sp,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            LinearProgressIndicator(

                progress = {

                    if (target == 0)
                        0f
                    else
                        current.toFloat() / target.toFloat()
                },

                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(

                text = "$current / $target",

                color = Color.LightGray
            )
        }
    }
}