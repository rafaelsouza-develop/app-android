package br.com.app4pets.app.repository.auth

import br.com.app4pets.app.network.models.LoginRequest
import br.com.app4pets.app.network.models.LoginResponse
import br.com.app4pets.app.network.models.RegisterRequest
import br.com.app4pets.app.network.models.RegisterResponse
import br.com.app4pets.app.network.Result

interface AuthRepository {

    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse>

    suspend fun register(registerRequest: RegisterRequest): Result<RegisterResponse>
}