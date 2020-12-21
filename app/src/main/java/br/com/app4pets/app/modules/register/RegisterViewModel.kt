package br.com.app4pets.app.modules.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.app4pets.app.base.BaseViewModel
import br.com.app4pets.app.base.ViewState
import br.com.app4pets.app.models.ResponseStatus
import br.com.app4pets.app.network.Result
import br.com.app4pets.app.network.models.LoginResponse
import br.com.app4pets.app.network.models.RegisterRequest
import br.com.app4pets.app.network.models.RegisterResponse
import br.com.app4pets.app.repository.auth.AuthRepository
import br.com.app4pets.app.util.DispatcherProvider
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: AuthRepository
) : BaseViewModel(dispatcherProvider) {

    private val _registerLiveData = MutableLiveData<ViewState<RegisterResponse, ResponseStatus>>()
    val registerLiveData: LiveData<ViewState<RegisterResponse, ResponseStatus>> = _registerLiveData

    fun register(registerRequest: RegisterRequest){
        scope.launch(dispatcherProvider.ui) {
            _registerLiveData.postValue(ViewState(status = ResponseStatus.LOADING))
            when (val response = repository.register(registerRequest)) {
                is Result.Success -> {
                    response.data.user?.id?.let {

                    }
                }
                is Result.Failure -> {

                }
            }
        }
    }
}