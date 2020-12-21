package br.com.app4pets.app.repository.auth

import br.com.app4pets.app.network.models.LoginResponse
import br.com.app4pets.app.network.AuthService
import br.com.app4pets.app.network.Result
import br.com.app4pets.app.network.models.LoginRequest
import br.com.app4pets.app.network.models.RegisterRequest
import br.com.app4pets.app.network.models.RegisterResponse


class AuthRepositoryImpl(private val authService: AuthService): AuthRepository {

    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
       val response = authService.login(loginRequest)
        if(response.isSuccessful){
            return Result.Success(response.body()!!)
        }
        return Result.Failure(Throwable("Error ${response.errorBody()} ${response.message()} "))
    }

    override suspend fun register(registerRequest: RegisterRequest): Result<RegisterResponse> {
        val response = authService.register(registerRequest)
        if(response.isSuccessful){
            return Result.Success(response.body()!!)
        }
        return Result.Failure(Throwable("Error ${response.errorBody()} ${response.message()} "))
    }
}