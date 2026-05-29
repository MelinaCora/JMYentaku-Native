package com.jmyentaku.app.ui.components.GeneralComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmyentaku.app.R

@Composable
fun DrawerContent(

    onLogout: () -> Unit,
    onProfileClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onHomeClick: () -> Unit
) {

    Column(

        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(

                brush = Brush.verticalGradient(

                    colors = listOf(

                        Color(0xFF0F172A),
                        Color(0xFF111827),
                        Color(0xFF1E1B4B)
                    )
                )
            )
            .padding(20.dp)
    ) {

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        // LOGO + APP NAME
        Row(

            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(

                painter = painterResource(
                    id = R.drawable.bonsai_logo
                ),

                contentDescription = "Logo",

                modifier = Modifier
                    .size(55.dp)
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column {

                Text(
                    text = "JMYentaku",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Anime Community",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(40.dp)
        )

        HorizontalDivider(
            color = Color.Gray.copy(alpha = 0.3f)
        )

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        // HOME
        DrawerItem(

            icon = Icons.Default.Home,

            title = "Home",

            onClick = {

                onHomeClick()
            }
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // PROFILE
        DrawerItem(

            icon = Icons.Default.Person,

            title = "Profile",

            onClick = {

                onProfileClick()
            }
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // BOOKMARKS
        DrawerItem(

            icon = Icons.Default.Bookmarks,

            title = "Bookmarks",

            onClick = {

                onBookmarksClick()
            }
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // LOGOUT
        DrawerItem(

            icon = Icons.Default.ExitToApp,

            title = "Logout",

            onClick = onLogout
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Text(

            text = "Powered by Jikan API",

            color = Color.Gray,

            fontSize = 12.sp
        )
    }
}