package br.com.app4pets.app.domain.usecase.auth.login

import br.com.app4pets.app.domain.repository.AuthRepository

class LoginUseCaseImpl(
    private val authRepository: AuthRepository
) : LoginUseCase {

    override suspend fun login() {

    }
}