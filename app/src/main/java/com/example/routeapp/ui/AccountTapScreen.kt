package com.example.routeapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.routeapp.network.RetrofitInstance
import com.example.routeapp.models.UpdateProfileRequest
import com.example.routeapp.ui.theme.BluePrimary
import com.example.routeapp.ui.theme.OnErrorWhite
import com.example.routeapp.ui.theme.RedError
import com.example.routeapp.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun AccountTapScreen(navController: NavHostController, authViewModel: AuthViewModel) {

    val scope = rememberCoroutineScope()

    var fullName by remember { mutableStateOf("Mohamed Mohamed Nabil") }
    var email by remember { mutableStateOf("mohamed.N@gmail.com") }
    var password by remember { mutableStateOf("123456789") }
    var mobileNumber by remember { mutableStateOf("01122118855") }
    var address by remember { mutableStateOf("6th October, street 11.....") }
    var loading by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }

    val token by authViewModel.token.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Text(text = "Route E-Commerce App", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Welcome, Mohamed", style = MaterialTheme.typography.titleMedium)
        Text(text = email, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Your full name") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Your E-mail") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Your password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = mobileNumber,
            onValueChange = { mobileNumber = it },
            label = { Text("Your mobile number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Your Address") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(20.dp))

        if (message.isNotEmpty()) {
            Text(text = message, color = BluePrimary)
            Spacer(modifier = Modifier.height(10.dp))
        }

        Button(
            onClick = {
                if (token == null) {
                    message = "No token found. Please login again."
                    return@Button
                }
                loading = true
                message = ""
                scope.launch {
                    try {
                        val bearerToken = "Bearer $token"
                        val response = RetrofitInstance.api.updateProfile(
                            UpdateProfileRequest(fullName, email, password, mobileNumber, address),
                            bearerToken
                        )
                        if (response.isSuccessful) {
                            message = "Profile updated successfully"
                        } else {
                            message = "Failed to update profile"
                        }
                    } catch (e: Exception) {
                        message = "Error: ${e.localizedMessage}"
                    }
                    loading = false
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading
        ) {
            Text(text = if (loading) "Loading..." else "Update Profile")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                authViewModel.signOut {
                    navController.navigate("signin") {
                        popUpTo("account") { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = RedError)
        ) {
            Text(
                text = "Logout",
                color = OnErrorWhite
            )
        }
    }
}
