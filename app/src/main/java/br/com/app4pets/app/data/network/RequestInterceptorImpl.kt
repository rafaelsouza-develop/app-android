package br.com.app4pets.app.data.network

import android.content.Context
import androidx.navigation.ui.AppBarConfiguration
import br.com.app4pets.app.App4PetsApplication
import br.com.app4pets.app.data.local.CredentialsDaoImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class RequestInterceptorImpl {

    private lateinit var credentialsDao: CredentialsDaoImpl

    fun logger(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    fun setupOkHttp(): OkHttpClient.Builder {
        credentialsDao = CredentialsDaoImpl(App4PetsApplication.context)
        val okHttp = OkHttpClient.Builder()
        okHttp.addInterceptor(logger())
        okHttp.addInterceptor { chain ->
            var newRequest = chain.request()
            newRequest = newRequest.newBuilder().addHeader(
                "Authorization",
                "Bearer ${credentialsDao.getToken()}"
            ).build()
            return@addInterceptor chain.proceed(newRequest)
        }
        return okHttp
    }
}