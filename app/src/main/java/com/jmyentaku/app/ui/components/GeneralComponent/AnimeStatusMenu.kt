package com.jmyentaku.app.ui.components.GeneralComponent

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AnimeStatusMenu(

    expanded: Boolean,

    onDismiss: () -> Unit,

    onInProgress: () -> Unit,

    onCompleted: () -> Unit,

    onPlanned: () -> Unit
) {

    DropdownMenu(

        expanded = expanded,

        onDismissRequest = onDismiss
    ) {

        DropdownMenuItem(

            text = {

                Text("In Progress")
            },

            onClick = onInProgress
        )

        DropdownMenuItem(

            text = {

                Text("Completed")
            },

            onClick = onCompleted
        )

        DropdownMenuItem(

            text = {

                Text("Planned")
            },

            onClick = onPlanned
        )
    }
}