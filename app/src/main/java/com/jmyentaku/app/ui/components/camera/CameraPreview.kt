package com.jmyentaku.app.ui.components.camera

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView

import androidx.core.content.ContextCompat

@Composable
fun CameraPreview(

    modifier: Modifier = Modifier,

    onImageCaptureReady: (ImageCapture) -> Unit

) {

    val context =
        LocalContext.current

    val lifecycleOwner =
        LocalLifecycleOwner.current

    AndroidView(

        modifier = modifier,

        factory = { ctx ->

            val previewView =
                PreviewView(ctx)

            val cameraProviderFuture =
                ProcessCameraProvider.getInstance(ctx)

            cameraProviderFuture.addListener({

                val cameraProvider =
                    cameraProviderFuture.get()

                val preview =
                    Preview.Builder()
                        .build()

                val imageCapture =
                    ImageCapture.Builder()
                        .build()

                onImageCaptureReady(
                    imageCapture
                )

                preview.surfaceProvider =
                    previewView.surfaceProvider

                try {

                    cameraProvider.unbindAll()

                    cameraProvider.bindToLifecycle(

                        lifecycleOwner,

                        CameraSelector.DEFAULT_BACK_CAMERA,

                        preview,

                        imageCapture
                    )

                } catch (e: Exception) {

                    e.printStackTrace()
                }

            }, ContextCompat.getMainExecutor(ctx))

            previewView
        }
    )
}