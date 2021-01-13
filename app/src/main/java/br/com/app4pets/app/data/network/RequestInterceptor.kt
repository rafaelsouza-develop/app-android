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
            var newRequest = chain.request()
            newRequest = newRequest.newBuilder().addHeader(
                "Authorization",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVmZjg4ZmUwN2I0NDg4MDAxN2Q1YWNkOCIsImlhdCI6MTYxMDU0MjcxNywiZXhwIjoxNjEwNjI5MTE3fQ.od7OPXQ6wUStPh7caAjE5hm8DLx3GYh6ufEJzkS7P68").build()
            return@addInterceptor chain.proceed(newRequest)
        }
        return okHttp
    }
}