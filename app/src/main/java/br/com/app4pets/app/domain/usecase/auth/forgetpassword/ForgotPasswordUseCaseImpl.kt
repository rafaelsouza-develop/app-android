package br.com.app4pets.app.domain.usecase.auth.forgetpassword

import br.com.app4pets.app.domain.repository.AuthRepository

class ForgotPasswordUseCaseImpl(
    private val authRepository: AuthRepository
): ForgotPasswordUseCase {

    override suspend fun forgetPassword() {

    }
}