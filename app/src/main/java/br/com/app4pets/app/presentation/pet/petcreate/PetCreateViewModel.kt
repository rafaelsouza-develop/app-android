package br.com.app4pets.app.presentation.pet.petcreate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.app4pets.app.base.ViewState
import br.com.app4pets.app.data.network.Result
import br.com.app4pets.app.data.network.models.PetRequest
import br.com.app4pets.app.domain.models.Pet
import br.com.app4pets.app.domain.models.ResponseStatus
import br.com.app4pets.app.domain.repository.PetsRepository
import br.com.app4pets.app.util.DispatcherProvider
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class PetCreateViewModel(private val dispatcherProvider: DispatcherProvider,
                         private val petsRepository: PetsRepository) :
    ViewModel() {

    private val _createPetLiveData = MutableLiveData<ViewState<Pet, ResponseStatus>>()
    val createPetLiveData: LiveData<ViewState<Pet, ResponseStatus>> = _createPetLiveData

    fun createPet(thumbnail: MultipartBody.Part, petRequest: PetRequest){
        _createPetLiveData.postValue(ViewState(status = ResponseStatus.LOADING))
        viewModelScope.launch {
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