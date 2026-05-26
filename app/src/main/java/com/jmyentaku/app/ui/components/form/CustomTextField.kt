package com.jmyentaku.app.ui.components.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {

    OutlinedTextField(
        value = value,

        onValueChange = onValueChange,

        label = {
            Text(
                text = label,
                color = Color.LightGray
            )
        },

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(16.dp),

        colors = OutlinedTextFieldDefaults.colors(

            focusedBorderColor = Color(0xFF2563EB),

            unfocusedBorderColor = Color.Gray,

            focusedTextColor = Color.White,

            unfocusedTextColor = Color.White,

            cursorColor = Color(0xFF2563EB)
        )
    )
}