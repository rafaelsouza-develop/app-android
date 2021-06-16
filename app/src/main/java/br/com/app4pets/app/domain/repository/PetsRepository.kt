package br.com.app4pets.app.domain.repository

import br.com.app4pets.app.domain.models.Pet
import br.com.app4pets.app.data.network.Result
import br.com.app4pets.app.data.network.models.PetRequest
import okhttp3.MultipartBody

interface PetsRepository {

    suspend fun listPets(): Result<ArrayList<Pet>>

    suspend fun createPet(thumbinal: MultipartBody.Part, pet: PetRequest) : Result<Pet>
}