package com.example.routeapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.routeapp.viewmodel.AuthViewModel
import androidx.compose.ui.Modifier

@Composable
fun RegisterScreen(navController: NavController) {

    val viewModel: AuthViewModel = viewModel()

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(Modifier.padding(24.dp)) {

        OutlinedTextField(name, { name = it }, label = { Text("Full Name") })
        OutlinedTextField(phone, { phone = it }, label = { Text("Mobile") })
        OutlinedTextField(email, { email = it }, label = { Text("Email") })
        OutlinedTextField(password, { password = it }, label = { Text("Password") })

        Button(onClick = {
            viewModel.signup(name, email, password, phone, {} ) {
                navController.navigate("signin")
            }
        }) {
            Text("Sign up")
        }
    }
}
