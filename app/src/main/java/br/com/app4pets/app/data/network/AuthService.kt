package br.com.app4pets.app.data.network

import br.com.app4pets.app.data.network.models.LoginRequest
import br.com.app4pets.app.data.network.models.LoginResponse
import br.com.app4pets.app.data.network.models.RegisterRequest
import br.com.app4pets.app.data.network.models.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/authenticate")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("auth/forgot_password")
    suspend fun forgotPassword(@Body email: String): Response<Boolean>
}