package br.com.app4pets.app.di

import br.com.app4pets.app.repository.auth.AuthRepository
import br.com.app4pets.app.repository.auth.AuthRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
}