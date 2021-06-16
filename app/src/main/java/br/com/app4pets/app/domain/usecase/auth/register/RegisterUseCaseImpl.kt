package br.com.app4pets.app.domain.usecase.auth.register

import br.com.app4pets.app.domain.repository.AuthRepository

class RegisterUseCaseImpl(
    private val authRepository: AuthRepository
) : RegisterUseCase {

    override suspend fun register() {

    }
}