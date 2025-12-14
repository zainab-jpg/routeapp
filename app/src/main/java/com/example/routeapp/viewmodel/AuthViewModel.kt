package com.example.routeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.routeapp.api.RetrofitInstance
import com.example.routeapp.data.*
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = DataStoreManager(application)

    fun signin(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.signin(
                    SigninRequest(email, password)
                )

                dataStore.saveToken(response.token)
                onSuccess()
            } catch (e: Exception) {
                onError("Email or password is wrong")
            }
        }
    }

    fun signup(
        name: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.signup(
                    SignupRequest(name, email, password, phone)
                )
                onSuccess()
            } catch (e: Exception) {
                onError("Signup failed")
            }
        }
    }
}
