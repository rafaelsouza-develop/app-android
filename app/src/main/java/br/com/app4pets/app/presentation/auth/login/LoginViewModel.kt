package br.com.app4pets.app.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
) : ViewModel(){

    private val _loginLiveData = MutableLiveData<ViewState<LoginResponse, ResponseStatus>>()
    val loginLiveData: LiveData<ViewState<LoginResponse, ResponseStatus>> = _loginLiveData


    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch(dispatcherProvider.ui) {
            _loginLiveData.postValue(ViewState(status = ResponseStatus.LOADING))
            when (val response = repository.login(loginRequest)) {
                is Result.Success -> {
                    _loginLiveData.postValue(ViewState(status = ResponseStatus.UNLOADING))
                    response.data.token?.let {
                        credentialsDao.saveToken(it)
                        _loginLiveData.postValue(
                            ViewState(
                                data = response.data,
                                status = ResponseStatus.SUCCESS
                            )
                        )
                    }
                }
                is Result.Failure -> {
                    _loginLiveData.postValue(ViewState(status = ResponseStatus.UNLOADING))
                    _loginLiveData.postValue(
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