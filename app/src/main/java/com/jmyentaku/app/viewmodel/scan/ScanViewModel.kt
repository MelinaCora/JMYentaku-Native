package com.jmyentaku.app.viewmodel.scan

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import android.content.Context
import android.net.Uri
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.content.ContextCompat
import java.io.File

class ScanViewModel : ViewModel() {

    // Ruta de la última foto capturada.
    var capturedImageUri by mutableStateOf<Uri?>(null)
        private set

    // Instancia de CameraX utilizada para tomar fotos.
    var imageCapture by mutableStateOf<ImageCapture?>(null)
        private set

    //instancia para el texto detectado en la imagen
    var detectedText by mutableStateOf("")
        private set

    // ==========================================
    // Guarda la foto que YA fue tomada.
    // Se utiliza para mostrar la imagen capturada
    // en la pantalla o enviarla a futuros procesos
    // como OCR o reconocimiento de portadas.
    // ==========================================
    fun updateCapturedImage(
        uri: Uri
    ) {
        capturedImageUri = uri
    }

    // ==========================================
    // Guarda la instancia de ImageCapture que
    // entrega CameraX.
    //
    // Esta instancia representa la cámara lista
    // para tomar fotografías.
    //
    // Más adelante será utilizada por takePhoto()
    // cuando el usuario presione el botón
    // "📸 Capture".
    // ==========================================
    fun updateImageCapture(
        capture: ImageCapture
    ) {
        imageCapture = capture
    }

    fun takePhoto(
        context: Context
    ) {

        val imageCapture =
            imageCapture ?: return

        val photoFile = File(

            context.cacheDir,

            "manga_scan_${System.currentTimeMillis()}.jpg"
        )

        val outputOptions =

            ImageCapture.OutputFileOptions.Builder(
                photoFile
            ).build()

        imageCapture.takePicture(

            outputOptions,

            ContextCompat.getMainExecutor(context),

            object : ImageCapture.OnImageSavedCallback {

                override fun onImageSaved(

                    outputFileResults: ImageCapture.OutputFileResults
                ) {

                    updateCapturedImage(
                        Uri.fromFile(photoFile)
                    )
                }

                override fun onError(

                    exception: ImageCaptureException
                ) {

                    exception.printStackTrace()
                }
            }
        )
    }

    fun updateDetectedText(
        text: String
    ) {
        detectedText = text
    }
}