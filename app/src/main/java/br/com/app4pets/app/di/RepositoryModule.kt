package br.com.app4pets.app.di

import br.com.app4pets.app.domain.repository.AuthRepository
import br.com.app4pets.app.data.repository.auth.AuthRepositoryImpl
import br.com.app4pets.app.domain.repository.PetsRepository
import br.com.app4pets.app.data.repository.pets.PetsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
    factory<PetsRepository> { PetsRepositoryImpl(get()) }
}