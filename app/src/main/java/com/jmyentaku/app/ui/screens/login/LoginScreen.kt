package com.jmyentaku.app.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jmyentaku.app.ui.components.GeneralComponent.CustomButton
import com.jmyentaku.app.ui.components.form.CustomTextField
import com.jmyentaku.app.ui.navigation.Routes
import com.jmyentaku.app.viewmodel.login.LoginViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jmyentaku.app.R

@Composable
fun LoginScreen(
    navController: NavController
) {

    val viewModel: LoginViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0F172A),
                        Color(0xFF1E1B4B),
                        Color(0xFF312E81)
                    )
                )
            )
            .padding(24.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.bonsai_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .height(140.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Welcome to JMYentaku",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Your anime universe in one place ✨",
            color = Color.LightGray,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF111827)
            )
        ) {

            Column(
                modifier = Modifier
                    .padding(24.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                CustomTextField(
                    value = viewModel.uiState.email,
                    onValueChange = {
                        viewModel.onEmailChange(it)
                    },
                    label = "Email"
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = viewModel.uiState.password,
                    onValueChange = {
                        viewModel.onPasswordChange(it)
                    },
                    label = "Password"
                )

                Spacer(modifier = Modifier.height(24.dp))

                if (viewModel.uiState.isLoading) {

                    CircularProgressIndicator()

                } else {

                    CustomButton(
                        text = "Login",
                        onClick = {
                            viewModel.login {

                                navController.navigate(Routes.Home.route)
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                CustomButton(
                    text = "Create Account",
                    onClick = {
                        navController.navigate(Routes.Register.route)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = viewModel.uiState.error ?: "",
                    color = Color.Red
                )
            }
        }
    }
}