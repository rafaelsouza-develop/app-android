package br.com.app4pets.app.di

import br.com.app4pets.app.repository.auth.AuthRepository
import br.com.app4pets.app.repository.auth.AuthRepositoryImpl
import br.com.app4pets.app.repository.pets.PetsRepository
import br.com.app4pets.app.repository.pets.PetsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
    factory<PetsRepository> { PetsRepositoryImpl(get()) }
}