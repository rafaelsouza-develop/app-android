package br.com.app4pets.app.testeutils

import br.com.app4pets.app.util.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestCoroutineContextProvider : DispatcherProvider() {

    override val ui: CoroutineContext = Dispatchers.Unconfined
    override val io: CoroutineContext = Dispatchers.Unconfined
}