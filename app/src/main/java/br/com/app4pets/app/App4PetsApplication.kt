package br.com.app4pets.app

import android.app.Application
import android.content.Context
import br.com.app4pets.app.di.localDataBaseModule
import br.com.app4pets.app.di.networkModule
import br.com.app4pets.app.di.repositoryModule
import br.com.app4pets.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App4PetsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupApp(baseContext)
    }

    private fun setupApp(context: Context) {
        startKoin {
            androidContext(context)
            modules(
                listOf(
                    viewModelModule,
                    networkModule,
                    repositoryModule,
                    localDataBaseModule
                )
            )
        }
    }
}