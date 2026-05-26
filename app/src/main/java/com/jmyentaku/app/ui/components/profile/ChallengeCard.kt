package com.jmyentaku.app.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ChallengeCard(
    title: String
) {

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .background(

                brush = Brush.horizontalGradient(

                    colors = listOf(

                        Color(0xFF1E293B),
                        Color(0xFF0F172A)
                    )
                ),

                shape = RoundedCornerShape(18.dp)
            )
            .padding(16.dp)
    ) {

        Text(

            text = title,

            color = Color.White,

            fontWeight = FontWeight.Bold
        )

        androidx.compose.foundation.layout.Spacer(
            modifier = Modifier.padding(4.dp)
        )

        LinearProgressIndicator(

            progress = { 0.6f },

            color = Color(0xFF38BDF8),

            trackColor = Color.DarkGray
        )
    }
}

