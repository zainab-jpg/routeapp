package com.example.routeapp.network
import com.example.routeapp.models.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("auth/signin")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    @POST("auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @PATCH("users/updateMe/")
    suspend fun updateProfile(@Body request: UpdateProfileRequest,
                              @Header("Authorization") token: String): Response<UpdateProfileResponse>

    @POST("auth/verifyToken")
    suspend fun verifyToken(@Body request: VerifyTokenRequest): Response<VerifyTokenResponse>
}
