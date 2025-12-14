package com.example.routeapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF004182))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Register", color = Color.White, style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(name, { name = it }, label = { Text("Full Name") },
            shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth())

        OutlinedTextField(phone, { phone = it }, label = { Text("Mobile") },
            shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth())

        OutlinedTextField(email, { email = it }, label = { Text("Email") },
            shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth())

        OutlinedTextField(
            password,
            { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
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
            }
        ) {
            Text("Create Account")
        }
    }
}
