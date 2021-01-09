package br.com.app4pets.app.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.app4pets.app.data.network.models.LoginResponse
import br.com.app4pets.app.models.ResponseStatus
import br.com.app4pets.app.util.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.KoinComponent

open class BaseViewModel(dispatcherProvider: DispatcherProvider) : ViewModel(), KoinComponent {

    private val viewModelJob = SupervisorJob()
    protected val scope = CoroutineScope(dispatcherProvider.io + viewModelJob)



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}