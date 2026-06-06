package com.jmyentaku.app.viewmodel.scan

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.camera.core.ImageCapture

class ScanViewModel : ViewModel() {

    var imageCapture by mutableStateOf<ImageCapture?>(null)
        private set

    fun updateImageCapture(
        capture: ImageCapture
    ) {
        imageCapture = capture
    }
}