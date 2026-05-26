package com.jmyentaku.app.ui.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmyentaku.app.R

@Composable
fun ProfileHeader() {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {

        Column(

            modifier = Modifier
                .background(

                    brush = Brush.verticalGradient(

                        colors = listOf(

                            Color(0xFF1E293B),
                            Color(0xFF0F172A)
                        )
                    )
                )
                .padding(20.dp)
        ) {

            Row(

                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(

                    painter = painterResource(
                        id = R.drawable.bonsai_logo
                    ),

                    contentDescription = "Profile Image",

                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                )

                Spacer(
                    modifier = Modifier.width(18.dp)
                )

                Column(

                    verticalArrangement = Arrangement.Center
                ) {

                    Text(

                        text = "Melina",

                        color = Color.White,

                        fontSize = 26.sp,

                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(

                        text = "Anime Explorer Lv.12",

                        color = Color(0xFF38BDF8),

                        fontSize = 15.sp
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(

                        text = "🔥 27 Day Streak",

                        color = Color.LightGray,

                        fontSize = 13.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            Text(

                text = "Level Progress",

                color = Color.White,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            LinearProgressIndicator(

                progress = { 0.72f },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp),

                color = Color(0xFF38BDF8),

                trackColor = Color.DarkGray
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(

                text = "720 XP / 1000 XP",

                color = Color.LightGray,

                fontSize = 12.sp
            )
        }
    }
}