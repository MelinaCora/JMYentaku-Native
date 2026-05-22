package com.jmyentaku.app.ui.components.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),

        value = value,

        onValueChange = onValueChange,

        label = {
            Text(text = label)
        }
    )
}