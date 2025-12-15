package com.example.routeapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
fun RegisterScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
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

                Spacer(Modifier.height(16.dp))



                AuthField("Full Name", "enter your full name", name,
                    onValueChange ={newValue -> name=newValue} )

                AuthField("Mobile Number", "enter your mobile no.", phone,
                    onValueChange = {phone=it})

                AuthField("E-mail address", "enter your email address", email,
                    onValueChange = {email=it})
                AuthField(
                    "Password",
                    "enter your password",
                    password,

                    onValueChange = {password=it},
                    isPassword = true)


                Spacer(Modifier.height(12.dp))

                Button(
                    onClick = {

                        viewModel.signup(
                            name,
                            email,
                            password,
                            phone,
                            onSuccess = {
                                navController.navigate("signin")
                            },
                            onError = {}
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = White)
                ) {
                    Text("Sign up", color = RouteBlue)
                }
            }
        }
    }
}
