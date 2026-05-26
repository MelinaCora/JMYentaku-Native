package com.jmyentaku.app.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileStatCard(
    title: String,
    value: String,
    emoji: String,
    modifier: Modifier = Modifier
) {

    Card(

        modifier = modifier
            .aspectRatio(1f),

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .background(

                    brush = Brush.verticalGradient(

                        colors = listOf(

                            Color(0xFF1E293B),
                            Color(0xFF0F172A)
                        )
                    )
                )
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = emoji,
                fontSize = 32.sp
            )

            Text(

                text = value,

                color = Color.White,

                fontSize = 26.sp,

                fontWeight = FontWeight.Bold
            )

            Text(

                text = title,

                color = Color.LightGray,

                fontSize = 14.sp
            )
        }
    }
}