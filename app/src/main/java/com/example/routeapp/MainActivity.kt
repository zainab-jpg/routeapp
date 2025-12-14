package com.example.routeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.routeapp.ui.AccountTapScreen
import com.example.routeapp.ui.RegisterScreen
import com.example.routeapp.ui.SignInScreen
import com.example.routeapp.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {

    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "signin") {
                composable("signin") {
                    SignInScreen(navController, authViewModel)
                }
                composable("register") {
                    RegisterScreen(navController, authViewModel)
                }
                composable("account") {
                    AccountTapScreen(navController, authViewModel)
                }
            }
        }
    }
}