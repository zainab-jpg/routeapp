package com.example.routeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.routeapp.ui.*

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = "signin") {

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
