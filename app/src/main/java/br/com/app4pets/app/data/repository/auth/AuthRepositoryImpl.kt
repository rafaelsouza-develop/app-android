package br.com.app4pets.app.data.repository.auth

import br.com.app4pets.app.data.network.AuthService
import br.com.app4pets.app.data.network.Result
import br.com.app4pets.app.data.network.models.LoginRequest
import br.com.app4pets.app.data.network.models.LoginResponse
import br.com.app4pets.app.data.network.models.RegisterRequest
import br.com.app4pets.app.data.network.models.RegisterResponse
import br.com.app4pets.app.domain.repository.AuthRepository


class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {

    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        val response = authService.login(loginRequest)
        if (response.isSuccessful) {
            return Result.Success(response.body()!!)
        }
        return Result.Failure(Throwable("Error ${response.errorBody()} ${response.message()} "))
    }

    override suspend fun register(registerRequest: RegisterRequest): Result<RegisterResponse> {
        val response = authService.register(registerRequest)
        if (response.isSuccessful) {
            return Result.Success(response.body()!!)
        }
        return Result.Failure(Throwable("Error ${response.errorBody()} ${response.message()} "))
    }

    override suspend fun forgotPassword(email: String): Result<Boolean> {
        val response = authService.forgotPassword(email)
        if (response.isSuccessful) {
            return Result.Success(response.body()!!)
        }
        return Result.Failure(Throwable("Error ${response.errorBody()} ${response.message()} "))
    }
}