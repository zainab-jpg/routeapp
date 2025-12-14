package com.example.routeapp.ui
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.routeapp.ui.theme.BluePrimary
import com.example.routeapp.ui.theme.RedError
import com.example.routeapp.viewmodel.AuthViewModel



@Composable
fun SignInScreen(navController: NavHostController, authViewModel: AuthViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val errorMessage by authViewModel.errorMessage.collectAsState()
    var loading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
           ,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Route", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Welcome Back To Route\nPlease sign in with your mail")
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            label = { Text("User Name") },
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            trailingIcon = {
                Text(text = "Forgot password", modifier = Modifier.clickable { /*TODO*/ }, color = BluePrimary)
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = RedError, modifier = Modifier.padding(bottom = 10.dp))
        }
        Button(
            onClick = {
                loading = true
                authViewModel.signIn(email.trim(), password.trim()) {
                    loading = false
                    navController.navigate("account") {
                        popUpTo("signin") { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading
        ) {
            Text(text = if (loading) "Loading..." else "Login")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Don't have an account? Create Account",
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("register")
                },
            color = BluePrimary
        )
    }
}

