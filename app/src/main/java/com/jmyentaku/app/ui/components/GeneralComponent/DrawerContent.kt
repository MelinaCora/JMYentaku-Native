package com.jmyentaku.app.ui.components.GeneralComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerContent(
    onLogout: () -> Unit
) {
    ModalDrawerSheet(
        modifier = Modifier.width(220.dp)  // tamaño fijo de momento, aca tendriamos que hacer que sea responsive
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text = "JMYentaku",
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Divider()

            Spacer(modifier = Modifier.height(16.dp))

            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Cerrar Sesión"
                    )
                },
                label = {
                    Text(text = "Cerrar Sesión")
                },
                selected = false,
                onClick = onLogout
            )
        }
    }
}