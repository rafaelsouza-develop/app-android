package br.com.app4pets.app.data.network

sealed class Result<out T> {
    class Success<out T>(val data: T) : Result<T>()
    class Failure(val throwable: Throwable) : Result<Nothing>()
}