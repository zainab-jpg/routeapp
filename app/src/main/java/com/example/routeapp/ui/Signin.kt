package com.example.routeapp.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.routeapp.viewmodel.AuthViewModel

@Composable
fun SigninScreen(navController: NavController) {

    val viewModel: AuthViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(Modifier.padding(24.dp)) {

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )

        Button(onClick = {
            viewModel.signin(
                email,
                password,
                onSuccess = {
                    navController.navigate("account") {
                        popUpTo("signin") { inclusive = true }
                    }
                },
                onError = { error = it }
            )
        }) {
            Text("Login")
        }

        if (error.isNotEmpty()) {
            Text(error, color = Color.Red)
        }

        TextButton(onClick = {
            navController.navigate("register")
        }) {
            Text("Create Account")
        }
    }
}
