package com.jmyentaku.app.ui.screens.scan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.jmyentaku.app.ui.components.camera.CameraPreview
@Composable
fun ScanMangaScreen() {

    val context = LocalContext.current

    var hasCameraPermission by remember {

        mutableStateOf(

            ContextCompat.checkSelfPermission(

                context,

                Manifest.permission.CAMERA

            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher =

        rememberLauncherForActivityResult(

            ActivityResultContracts.RequestPermission()

        ) { granted ->

            hasCameraPermission = granted
        }

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

                    if (!hasCameraPermission) {

                        permissionLauncher.launch(
                            Manifest.permission.CAMERA
                        )
                    }

                }
            ) {

                Text(
                    text = "Open Camera"
                )
            }
            Spacer(
                modifier = Modifier.height(16.dp)
            )

            if (hasCameraPermission) {

                CameraPreview(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                )

            } else {

                Text(

                    text = "❌ Camera permission required"
                )
            }



        }
    }
}