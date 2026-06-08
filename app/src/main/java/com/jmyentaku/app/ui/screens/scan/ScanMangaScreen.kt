package com.jmyentaku.app.ui.screens.scan

import android.Manifest
import android.content.pm.PackageManager

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color

import androidx.core.content.ContextCompat

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.jmyentaku.app.ui.components.camera.CameraPreview
import com.jmyentaku.app.viewmodel.scan.ScanViewModel
import com.jmyentaku.app.ui.navigation.Routes

@Composable
fun ScanMangaScreen(
    navController: NavController,
    scanViewModel: ScanViewModel
) {

    val context = LocalContext.current

    val capturedImageUri = scanViewModel.capturedImageUri

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

            modifier = Modifier.fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(

                text = "📷 Scan Manga",

                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            if (!hasCameraPermission) {

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    onClick = {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                ) {
                    Text("Open Camera")
                }

            } else {

                CameraPreview(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),

                    onImageCaptureReady = {

                        scanViewModel.updateImageCapture(it)
                    }
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    onClick = {
                        scanViewModel.takePhoto(context)
                    }
                ) {
                    Text("📸 Capture")
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                //miniatura
                if (capturedImageUri != null) {

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    androidx.compose.foundation.Image(
                        painter = coil.compose.rememberAsyncImagePainter(
                            capturedImageUri
                        ),
                        contentDescription = "Captured Image",
                        modifier = Modifier
                            .size(150.dp)
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )
                }

                Button(
                    enabled = capturedImageUri != null,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        disabledContainerColor = Color(0xFF334155)
                    ),
                    onClick = {

                        scanViewModel.recognizeText(context) { found ->
                            if (found) {
                                navController.navigate(Routes.OCRResults.route)
                            } else {
                                navController.navigate(Routes.ManualManga.route)
                            }
                        }
                    }
                ) {
                    Text("🔍 Detect")
                }
            }
        }
    }
}