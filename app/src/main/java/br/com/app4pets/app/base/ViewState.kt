package br.com.app4pets.app.base

class ViewState<T,S>(
    val data: T? = null,
    val status: S,
    val error: Throwable? = null
)