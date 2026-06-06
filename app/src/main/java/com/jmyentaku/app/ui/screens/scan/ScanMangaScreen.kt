package com.jmyentaku.app.ui.screens.scan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScanMangaScreen() {

    Box(

        modifier = Modifier.fillMaxSize(),

        contentAlignment = Alignment.Center
    ) {

        Column(

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(

                text = "📷 Scan Manga"
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(

                onClick = {

                }
            ) {

                Text(
                    text = "Open Camera"
                )
            }
        }
    }
}