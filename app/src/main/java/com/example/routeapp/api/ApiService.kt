package com.example.routeapp.api
import com.example.routeapp.data.*
import retrofit2.http.*

interface ApiService {

    @POST("auth/signin")
    suspend fun signin(@Body body: SigninRequest): SigninResponse

    @POST("auth/signup")
    suspend fun signup(@Body body: SignupRequest): SignupResponse

    @GET("auth/verifyToken")
    suspend fun verifyToken(@Header("token") token: String)

    @PUT("users/updateMe")
    suspend fun updateUser(
        @Header("token") token: String,
        @Body body: UpdateUserRequest
    ): UpdateUserResponse
}
