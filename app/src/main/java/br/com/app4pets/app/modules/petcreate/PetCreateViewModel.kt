package br.com.app4pets.app.modules.petcreate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.app4pets.app.base.BaseViewModel
import br.com.app4pets.app.base.ViewState
import br.com.app4pets.app.data.network.Result
import br.com.app4pets.app.data.network.models.PetRequest
import br.com.app4pets.app.models.Pet
import br.com.app4pets.app.models.ResponseStatus
import br.com.app4pets.app.repository.pets.PetsRepository
import br.com.app4pets.app.util.DispatcherProvider
import kotlinx.coroutines.launch

class PetCreateViewModel(private val dispatcherProvider: DispatcherProvider, private val petsRepository: PetsRepository) :
    BaseViewModel(dispatcherProvider) {

    private val _createPetLiveData = MutableLiveData<ViewState<Pet, ResponseStatus>>()
    val createPetLiveData: LiveData<ViewState<Pet, ResponseStatus>> = _createPetLiveData

    fun createPet(thumbnail:String, petRequest: PetRequest){
        scope.launch {
            when (val response = petsRepository.createPet(thumbnail, petRequest)) {
                is Result.Success -> {
                    _createPetLiveData.postValue(ViewState(status = ResponseStatus.UNLOADING))
                    response.data.let {

                        _createPetLiveData.postValue(ViewState(response.data, ResponseStatus.SUCCESS))
                    }
                }
                is Result.Failure -> {
                    _createPetLiveData.postValue(ViewState(status = ResponseStatus.UNLOADING))
                    _createPetLiveData.postValue(ViewState(null, ResponseStatus.ERROR, response.throwable))
                }
            }
        }
    }
}