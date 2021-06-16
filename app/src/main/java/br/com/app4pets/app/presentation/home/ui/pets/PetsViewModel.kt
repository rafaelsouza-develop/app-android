package br.com.app4pets.app.presentation.home.ui.pets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.app4pets.app.base.BaseViewModel
import br.com.app4pets.app.base.ViewState
import br.com.app4pets.app.data.network.Result
import br.com.app4pets.app.domain.models.Pet
import br.com.app4pets.app.domain.models.ResponseStatus
import br.com.app4pets.app.domain.repository.PetsRepository
import br.com.app4pets.app.util.DispatcherProvider
import kotlinx.coroutines.launch

class PetsViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val petsRepository: PetsRepository
) :
    BaseViewModel(dispatcherProvider) {

    private val _petsLiveData = MutableLiveData<ViewState<ArrayList<Pet>, ResponseStatus>>()
    val petsLiveData: LiveData<ViewState<ArrayList<Pet>, ResponseStatus>> = _petsLiveData

    fun listPets() {
        scope.launch(dispatcherProvider.ui) {
            _petsLiveData.postValue(ViewState(status = ResponseStatus.LOADING))
            when (val response = petsRepository.listPets()) {
                is Result.Success -> {
                    _petsLiveData.postValue(ViewState(status = ResponseStatus.UNLOADING))
                    if (response.data.size > 0) {
                        _petsLiveData.postValue(ViewState(response.data, ResponseStatus.SUCCESS))
                    } else {
                        _petsLiveData.postValue(ViewState(null, ResponseStatus.EMPTY_LIST))
                    }
                }
                is Result.Failure -> {
                    _petsLiveData.postValue(ViewState(status = ResponseStatus.UNLOADING))
                    _petsLiveData.postValue(
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