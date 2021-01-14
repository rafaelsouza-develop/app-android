package br.com.app4pets.app

import android.app.Application
import android.content.Context
import br.com.app4pets.app.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class App4PetsApplication : Application() {

    companion object{
        lateinit var context: Context
    }


    override fun onCreate() {
        super.onCreate()
        setupApp(baseContext)
        context = this
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