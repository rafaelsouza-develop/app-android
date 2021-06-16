package br.com.app4pets.app.di

import br.com.app4pets.app.presentation.home.ui.pets.PetsViewModel
import br.com.app4pets.app.presentation.auth.login.LoginViewModel
import br.com.app4pets.app.presentation.pet.petcreate.PetCreateViewModel
import br.com.app4pets.app.presentation.pet.petprofile.PetProfileViewModel
import br.com.app4pets.app.presentation.auth.register.RegisterViewModel
import br.com.app4pets.app.util.DispatcherProvider
import org.koin.dsl.module

val viewModelModule = module {

    factory { DispatcherProvider() }
    factory { LoginViewModel(get(), get(), get()) }
    factory { RegisterViewModel(get(), get()) }
    factory { PetsViewModel(get(), get()) }
    factory { PetProfileViewModel(get()) }
    factory { PetCreateViewModel(get(), get()) }
}