package com.jmyentaku.app.ui.components.workerTest

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.jmyentaku.app.workers.runTestWorker

@Composable
fun TestWorkerButton() {

    val context = LocalContext.current

    Button(
        onClick = {
            runTestWorker(context)
        }
    ) {
        Text("Probar WorkManager")
    }
}