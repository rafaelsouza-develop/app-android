package br.com.app4pets.app.di

import br.com.app4pets.app.data.local.CredentialsDao
import br.com.app4pets.app.data.local.CredentialsDaoImpl
import org.koin.dsl.module

val localDataBaseModule = module {
    factory<CredentialsDao> { CredentialsDaoImpl(get()) }
}