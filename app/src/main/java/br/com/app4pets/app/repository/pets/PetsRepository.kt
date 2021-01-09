package br.com.app4pets.app.repository.pets

import br.com.app4pets.app.models.Pet
import br.com.app4pets.app.data.network.Result
import br.com.app4pets.app.data.network.models.PetResponse

interface PetsRepository {

    suspend fun listPets(): Result<PetResponse>

    suspend fun createPet() : Result<Pet>
}