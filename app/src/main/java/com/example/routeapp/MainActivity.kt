package com.example.routeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.routeapp.ui.RegisterScreen
import com.example.routeapp.ui.SigninScreen
import com.example.routeapp.ui.AccountTab

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "signin"
            ) {
                composable("signin") {
                    SigninScreen(navController)
                }
                composable("register") {
                    RegisterScreen(navController)
                }
                composable("account") {
                    AccountTab(navController)
                }
            }
        }
    }
}



