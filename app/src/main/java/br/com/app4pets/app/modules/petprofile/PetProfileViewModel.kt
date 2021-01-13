package br.com.app4pets.app.modules.petprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.app4pets.app.base.BaseViewModel
import br.com.app4pets.app.base.ViewState
import br.com.app4pets.app.data.network.Result
import br.com.app4pets.app.data.network.models.LoginResponse
import br.com.app4pets.app.data.network.models.PetRequest
import br.com.app4pets.app.models.ResponseStatus
import br.com.app4pets.app.util.DispatcherProvider
import kotlinx.coroutines.launch

class PetProfileViewModel(private val dispatcherProvider: DispatcherProvider) :
    BaseViewModel(dispatcherProvider) {


}