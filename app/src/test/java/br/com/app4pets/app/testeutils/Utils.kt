package br.com.app4pets.app.testeutils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> createFlowTest(valueToEmit: T): Flow<T> = flow {
    emit(valueToEmit)
}