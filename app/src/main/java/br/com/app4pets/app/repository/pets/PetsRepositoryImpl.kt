package br.com.app4pets.app.repository.pets

import br.com.app4pets.app.data.network.PetService
import br.com.app4pets.app.data.network.Result
import br.com.app4pets.app.data.network.models.PetRequest
import br.com.app4pets.app.data.network.models.PetResponse
import br.com.app4pets.app.models.Pet
import okhttp3.MultipartBody
import org.koin.ext.scope

class PetsRepositoryImpl(private val petService: PetService) : PetsRepository {

    override suspend fun listPets(): Result<ArrayList<Pet>> {
        val response = petService.listPets()
        if (response.isSuccessful) {
            return Result.Success(response.body()!!)
        }
        return Result.Failure(Throwable("Error ${response.errorBody()} ${response.message()} "))
    }

    override suspend fun createPet(thumbinal: MultipartBody.Part, pet: PetRequest): Result<Pet> {
        val response =
            petService.createPet(thumbinal, pet.name, pet.breed, pet.dateOfBirth, pet.color)
        if (response.isSuccessful) {
            return Result.Success(response.body()!!)
        }
        return Result.Failure(Throwable("Error ${response.errorBody()} ${response.message()} "))
    }
}