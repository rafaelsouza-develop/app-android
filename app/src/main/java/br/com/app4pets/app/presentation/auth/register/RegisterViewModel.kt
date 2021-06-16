package br.com.app4pets.app.presentation.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.app4pets.app.base.BaseViewModel
import br.com.app4pets.app.base.ViewState
import br.com.app4pets.app.domain.models.ResponseStatus
import br.com.app4pets.app.data.network.Result
import br.com.app4pets.app.data.network.models.RegisterRequest
import br.com.app4pets.app.data.network.models.RegisterResponse
import br.com.app4pets.app.domain.repository.AuthRepository
import br.com.app4pets.app.util.DispatcherProvider
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: AuthRepository
) : BaseViewModel(dispatcherProvider) {

    private val _registerLiveData = MutableLiveData<ViewState<RegisterResponse, ResponseStatus>>()
    val registerLiveData: LiveData<ViewState<RegisterResponse, ResponseStatus>> = _registerLiveData

    fun register(registerRequest: RegisterRequest) {
        scope.launch(dispatcherProvider.ui) {
            _registerLiveData.postValue(ViewState(status = ResponseStatus.LOADING))
            when (val response = repository.register(registerRequest)) {
                is Result.Success -> {
                    _registerLiveData.postValue(ViewState(status = ResponseStatus.UNLOADING))
                    response.data.user?.id?.let {
                        _registerLiveData.postValue(
                            ViewState(
                                response.data,
                                ResponseStatus.SUCCESS
                            )
                        )

                    }
                }
                is Result.Failure -> {
                    _registerLiveData.postValue(ViewState(status = ResponseStatus.UNLOADING))
                    _registerLiveData.postValue(
                        ViewState(
                            null,
                            ResponseStatus.ERROR,
                            response.throwable
                        )
                    )
                }
            }
        }
    }
}