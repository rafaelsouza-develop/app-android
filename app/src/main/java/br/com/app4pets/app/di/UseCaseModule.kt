package br.com.app4pets.app.di

import br.com.app4pets.app.domain.usecase.auth.forgetpassword.ForgotPasswordUseCase
import br.com.app4pets.app.domain.usecase.auth.forgetpassword.ForgotPasswordUseCaseImpl
import br.com.app4pets.app.domain.usecase.auth.login.LoginUseCase
import br.com.app4pets.app.domain.usecase.auth.login.LoginUseCaseImpl
import br.com.app4pets.app.domain.usecase.auth.register.RegisterUseCase
import br.com.app4pets.app.domain.usecase.auth.register.RegisterUseCaseImpl
import br.com.app4pets.app.domain.usecase.pet.createpet.CreatePetUseCase
import br.com.app4pets.app.domain.usecase.pet.createpet.CreatePetUseCaseImpl
import br.com.app4pets.app.domain.usecase.pet.listpet.ListPetUseCase
import br.com.app4pets.app.domain.usecase.pet.listpet.ListPetUseCaseImpl
import br.com.app4pets.app.domain.usecase.pet.profile.PetProfileUseCase
import br.com.app4pets.app.domain.usecase.pet.profile.PetProfileUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {

    factory<LoginUseCase> { LoginUseCaseImpl(get()) }
    factory<RegisterUseCase> { RegisterUseCaseImpl(get()) }
    factory<ForgotPasswordUseCase> { ForgotPasswordUseCaseImpl(get()) }
    factory<ListPetUseCase> { ListPetUseCaseImpl(get()) }
    factory<PetProfileUseCase> { PetProfileUseCaseImpl(get()) }
    factory<CreatePetUseCase> { CreatePetUseCaseImpl(get()) }

}