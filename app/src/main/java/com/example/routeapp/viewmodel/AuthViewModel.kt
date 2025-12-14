package com.example.routeapp.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.routeapp.data.DataStoreManager
import com.example.routeapp.network.RetrofitInstance
import com.example.routeapp.models.SignInRequest
import com.example.routeapp.models.SignUpRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStoreManager = DataStoreManager(application.applicationContext)

    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> = _token

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    init {
        viewModelScope.launch {
            dataStoreManager.userToken.collect { savedToken ->
                _token.value = savedToken
            }
        }
        viewModelScope.launch {
            dataStoreManager.isLoggedIn.collect { loggedIn ->
                _isLoggedIn.value = loggedIn
            }
        }
    }

    fun signIn(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.signIn(SignInRequest(email, password))
                if (response.isSuccessful) {
                    val tokenValue = response.body()?.token
                    if (tokenValue != null) {
                        dataStoreManager.saveToken(tokenValue)
                        _errorMessage.value = ""
                        onSuccess()
                    } else {
                        _errorMessage.value = "Invalid credentials or no token."
                    }
                } else {
                    _errorMessage.value = "Login failed, check credentials."
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.localizedMessage}"
            }
        }
    }

    fun signUp(name: String, email: String, mobile: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.signUp(SignUpRequest(name, email, mobile, password))
                if (response.isSuccessful) {
                    _errorMessage.value = ""
                    onSuccess()
                } else {
                    _errorMessage.value = "Sign up failed. Try again."
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.localizedMessage}"
            }
        }
    }

    fun signOut(onComplete: () -> Unit) {
        viewModelScope.launch {
            dataStoreManager.clearToken()
            _errorMessage.value = ""
            onComplete()
        }
    }
}
