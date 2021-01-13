package br.com.app4pets.app.repository.pets

import br.com.app4pets.app.models.Pet
import br.com.app4pets.app.data.network.Result
import br.com.app4pets.app.data.network.models.PetRequest
import br.com.app4pets.app.data.network.models.PetResponse

interface PetsRepository {

    suspend fun listPets(): Result<ArrayList<Pet>>

    suspend fun createPet(thumbinal: String, pet: PetRequest) : Result<Pet>
}