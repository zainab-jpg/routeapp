package com.example.routeapp.models
data class SignInRequest(val email: String, val password: String)
data class SignInResponse(val token: String?, val status: String?, val message: String?)

data class SignUpRequest(
    val name: String,
    val email: String,
    val mobile: String,
    val password: String
)
data class SignUpResponse(val status: String?, val data: Any?, val message: String?)

data class UpdateProfileRequest(
    val name: String,
    val email: String,
    val password: String,
    val mobile: String,
    val address: String
)
data class UpdateProfileResponse(val status: String?, val message: String?, val data: Any?)

data class VerifyTokenRequest(val token: String)
data class VerifyTokenResponse(val status: String?, val valid: Boolean)
