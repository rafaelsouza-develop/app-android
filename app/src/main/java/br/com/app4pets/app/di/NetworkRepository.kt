package br.com.app4pets.app.di

import br.com.app4pets.app.data.network.AuthService
import br.com.app4pets.app.data.network.RequestInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://app4pets-backend.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(RequestInterceptor.setupOkHttp().build())
            .build()
    }
    single { get<Retrofit>().create(AuthService::class.java) }

}