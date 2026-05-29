package com.jmyentaku.app.ui.components.GeneralComponent

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(

    title: String,

    onMenuClick: () -> Unit
) {

    TopAppBar(

        colors = TopAppBarDefaults.topAppBarColors(

            containerColor = Color.Transparent,

            titleContentColor = Color.White
        ),

        title = {

            Text(

                text = title,

                fontWeight = FontWeight.Bold
            )
        },

        navigationIcon = {

            IconButton(

                onClick = {

                    onMenuClick()
                }
            ) {

                Icon(

                    imageVector = Icons.Default.Menu,

                    contentDescription = "Menu",

                    tint = Color.White
                )
            }
        }
    )
}