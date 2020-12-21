package br.com.app4pets.app.di

import br.com.app4pets.app.modules.login.LoginViewModel
import br.com.app4pets.app.util.DispatcherProvider
import org.koin.dsl.module

val viewModelModule = module {

    factory { DispatcherProvider() }
    factory { LoginViewModel(get(), get()) }
}