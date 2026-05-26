package com.jmyentaku.app.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun UserListCard(
    title: String
) {

    Row(

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
            .clickable { }
            .padding(18.dp),

        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = title,
            color = Color.White
        )

        Icon(

            imageVector = Icons.Default.KeyboardArrowRight,

            contentDescription = null,

            tint = Color(0xFF38BDF8)
        )
    }
}