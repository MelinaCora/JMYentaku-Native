package com.jmyentaku.app.viewmodel.scan

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import android.content.Context
import android.net.Uri
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.content.ContextCompat
import java.io.File

import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import com.jmyentaku.app.data.repository.MangaRepository
import com.jmyentaku.app.data.model.Anime

class ScanViewModel : ViewModel() {

    //instancia de mangaRepository
    private val mangaRepository =
        MangaRepository()

    // Ruta de la última foto capturada.
    var capturedImageUri by mutableStateOf<Uri?>(null)
        private set

    // Instancia de CameraX utilizada para tomar fotos.
    var imageCapture by mutableStateOf<ImageCapture?>(null)
        private set

    //instancia para el texto detectado en la imagen
    var detectedText by mutableStateOf("")
        private set

    // Manga encontrado mediante OCR + búsqueda Jikan
    var foundManga by mutableStateOf<Anime?>(null)
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

    fun recognizeText(
        context: Context
    ) {

        val imageUri =
            capturedImageUri ?: return

        try {

            val image =

                InputImage.fromFilePath(
                    context,
                    imageUri
                )

            val recognizer =

                TextRecognition.getClient(
                    TextRecognizerOptions.DEFAULT_OPTIONS
                )

            recognizer.process(image)

                .addOnSuccessListener { visionText ->

                    val text = visionText.text

                    updateDetectedText(text)

                    println("OCR DETECTED:")
                    println(text)

                    val query =

                        text.lines()
                            .firstOrNull()
                            ?.trim()
                            ?: text

                    searchDetectedManga(query)

                    println("QUERY SENT TO JIKAN:")
                    println(query)

                }

                .addOnFailureListener {

                    updateDetectedText(
                        "Error detecting text"
                    )
                }

        } catch (e: Exception) {

            updateDetectedText(
                "Error loading image"
            )
        }
    }

    fun searchDetectedManga(
        query: String
    ) {

        viewModelScope.launch {

            try {

                println("OCR TEXT: $query")

                val results =
                    mangaRepository.searchManga(query)

                println("OCR RESULTS FOUND: ${results.size}")

                results.forEach {

                    println(" OCR MANGA: ${it.title}")
                }

                foundManga =
                    results.firstOrNull()

            } catch (e: Exception) {

                println("ERROR TYPE: ${e.javaClass.simpleName}")
                println("ERROR MESSAGE: ${e.message}")

                e.printStackTrace()
            }
        }
    }

    /*fun searchDetectedManga(
        query: String
    ) {

        viewModelScope.launch {

            try {

                val results =

                    mangaRepository.searchManga(
                        query
                    )

                foundManga =
                    results.firstOrNull()

            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }*/
}