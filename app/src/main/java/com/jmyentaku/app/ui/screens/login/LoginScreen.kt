package com.jmyentaku.app.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jmyentaku.app.ui.navigation.Routes
import com.jmyentaku.app.ui.components.GeneralComponent.CustomButton
import com.jmyentaku.app.ui.components.form.CustomTextField

@Composable
fun LoginScreen(
    navController: NavController
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Login")

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = "Email"
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = "Password"
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomButton(
            text = "Login",
            onClick = {
                navController.navigate(Routes.Home.route)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomButton(
            text = "Registro",
            onClick = {
                navController.navigate(Routes.Register.route)
            }
        )
    }
}