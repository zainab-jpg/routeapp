package com.example.routeapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.routeapp.ui.components.AuthField
import com.example.routeapp.ui.theme.RouteBlue
import com.example.routeapp.ui.theme.ScreenBg
import com.example.routeapp.ui.theme.White
import com.example.routeapp.viewmodel.AuthViewModel

@Composable
fun SigninScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBg),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier.width(300.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = RouteBlue)
        ) {

            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    "Route",
                    color = White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(12.dp))

                Text("Welcome Back To Route", color = White)
                Text(
                    "Please sign in with your mail",
                    color = White.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )

                Spacer(Modifier.height(16.dp))


                AuthField(
                    label = "User Name",
                    hint = "enter your name",
                    value = email,
                    onValueChange = { email = it }
                )


                AuthField(
                    label = "Password",
                    hint = "enter your password",
                    value = password,
                    onValueChange = { password = it },
                    isPassword = true
                )

                Text(
                    "Forgot password",
                    color = White,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable {

                        }
                )

                Spacer(Modifier.height(12.dp))

                Button(
                    onClick = {

                        viewModel.signin(
                            email,
                            password,
                            onSuccess = {
                                navController.navigate("account") {
                                    popUpTo("signin") { inclusive = true }
                                }
                            },
                            onError = {}
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = White)
                ) {
                    Text("Login", color = RouteBlue)
                }

                Spacer(Modifier.height(8.dp))

                Row {
                    Text("Don't have an account? ", color = White, fontSize = 12.sp)
                    Text(
                        "Create Account",
                        color = White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        modifier = Modifier.clickable {
                            navController.navigate("register")
                        }
                    )
                }
            }
        }
    }
}
