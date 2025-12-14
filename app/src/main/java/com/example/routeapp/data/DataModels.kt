package com.example.routeapp.data

data class SigninRequest(
    val email: String,
    val password: String
)

data class SignupRequest(
    val name: String,
    val email: String,
    val password: String,
    val phone: String
)

data class UpdateUserRequest(
    val name: String,
    val phone: String
)

data class SigninResponse(
    val message: String,
    val token: String
)

data class SignupResponse(val message: String)
data class UpdateUserResponse(val message: String)
