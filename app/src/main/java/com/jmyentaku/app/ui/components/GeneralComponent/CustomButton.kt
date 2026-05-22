package com.jmyentaku.app.ui.components.GeneralComponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit
) {

    Button(
        modifier = Modifier.fillMaxWidth(),

        onClick = onClick
    ) {

        Text(text = text)
    }
}