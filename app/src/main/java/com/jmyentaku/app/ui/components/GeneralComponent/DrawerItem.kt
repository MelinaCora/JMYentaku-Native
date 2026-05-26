package com.jmyentaku.app.ui.components.GeneralComponent

//este componente es reutilizable para cada item del nav

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DrawerItem(

    icon: ImageVector,

    title: String,

    onClick: () -> Unit = {}
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .background(

                color = Color.White.copy(alpha = 0.06f),

                shape = RoundedCornerShape(14.dp)
            )
            .clickable {

                onClick()
            }
            .padding(16.dp),

        verticalAlignment = Alignment.CenterVertically,

        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        Icon(

            imageVector = icon,

            contentDescription = title,

            tint = Color.White
        )

        Text(

            text = title,

            color = Color.White,

            fontWeight = FontWeight.SemiBold
        )
    }
}