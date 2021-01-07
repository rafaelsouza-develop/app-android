package br.com.app4pets.app.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RequestInterceptor {

    fun logger(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    fun setupOkHttp(): OkHttpClient.Builder {
        val okHttp = OkHttpClient.Builder()
        okHttp.addInterceptor(logger())
        okHttp.addInterceptor { chain ->
            return@addInterceptor chain.proceed(chain.request())
        }
        return okHttp
    }
}