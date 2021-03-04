package br.com.app4pets.app

import android.app.Application
import android.content.Context
import br.com.app4pets.app.di.localDataBaseModule
import br.com.app4pets.app.di.networkModule
import br.com.app4pets.app.di.repositoryModule
import br.com.app4pets.app.di.viewModelModule
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


open class App4PetsApplication : Application() {

    companion object {
        lateinit var context: Context
        lateinit var tokenFirebase: String
    }


    override fun onCreate() {
        super.onCreate()
        setupApp(baseContext)
        context = this
        with(FirebaseMessaging.getInstance()) {
            subscribeToTopic("teste_push")
            tokenFirebase = token.toString()
        }
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