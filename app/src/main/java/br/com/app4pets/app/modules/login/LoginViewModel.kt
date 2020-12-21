package br.com.app4pets.app.modules.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.app4pets.app.base.BaseViewModel
import br.com.app4pets.app.base.ViewState
import br.com.app4pets.app.models.ResponseStatus
import br.com.app4pets.app.network.Result
import br.com.app4pets.app.network.models.LoginRequest
import br.com.app4pets.app.network.models.LoginResponse
import br.com.app4pets.app.repository.auth.AuthRepository
import br.com.app4pets.app.util.DispatcherProvider

import kotlinx.coroutines.launch

class LoginViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: AuthRepository
) : BaseViewModel(dispatcherProvider) {

    private val _loginLiveData = MutableLiveData<ViewState<LoginResponse, ResponseStatus>>()
    val loginLiveData: LiveData<ViewState<LoginResponse, ResponseStatus>> = _loginLiveData

    fun login(loginRequest: LoginRequest) {
        scope.launch(dispatcherProvider.ui) {
            _loginLiveData.postValue(ViewState(status = ResponseStatus.LOADING))
            when (val response = repository.login(loginRequest)) {
                is Result.Success -> {
                    response.data.token?.let {

                    }
                }
                is Result.Failure -> {

                }
            }
        }

    }
}