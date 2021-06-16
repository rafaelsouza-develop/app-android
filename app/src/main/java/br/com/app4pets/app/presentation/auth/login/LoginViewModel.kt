package br.com.app4pets.app.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.app4pets.app.base.BaseViewModel
import br.com.app4pets.app.base.ViewState
import br.com.app4pets.app.data.local.CredentialsDao
import br.com.app4pets.app.domain.models.ResponseStatus
import br.com.app4pets.app.data.network.Result
import br.com.app4pets.app.data.network.models.LoginRequest
import br.com.app4pets.app.data.network.models.LoginResponse
import br.com.app4pets.app.domain.repository.AuthRepository
import br.com.app4pets.app.util.DispatcherProvider

import kotlinx.coroutines.launch

class LoginViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: AuthRepository,
    private val credentialsDao: CredentialsDao
) : BaseViewModel(dispatcherProvider) {

    private val _loginLiveData = MutableLiveData<ViewState<LoginResponse, ResponseStatus>>()
    val loginLiveData: LiveData<ViewState<LoginResponse, ResponseStatus>> = _loginLiveData


    fun login(loginRequest: LoginRequest) {
        scope.launch(dispatcherProvider.ui) {
            _loginLiveData.postValue(ViewState(status = ResponseStatus.LOADING))
            when (val response = repository.login(loginRequest)) {
                is Result.Success -> {
                    _loginLiveData.postValue(ViewState(status = ResponseStatus.UNLOADING))
                    response.data.token?.let {
                        credentialsDao.saveToken(it)
                        _loginLiveData.postValue(ViewState(response.data, ResponseStatus.SUCCESS))
                    }
                }
                is Result.Failure -> {
                    _loginLiveData.postValue(ViewState(status = ResponseStatus.UNLOADING))
                    _loginLiveData.postValue(ViewState(null, ResponseStatus.ERROR, response.throwable))
                }
            }
        }

    }
}