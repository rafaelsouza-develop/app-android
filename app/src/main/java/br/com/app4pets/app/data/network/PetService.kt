package br.com.app4pets.app.data.network

import br.com.app4pets.app.data.network.models.PetRequest
import br.com.app4pets.app.data.network.models.PetResponse
import br.com.app4pets.app.models.Pet
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PetService {

    @GET("pet/index")
    suspend fun listPets(): Response<ArrayList<Pet>>

    @POST
    suspend fun createPet(@Body petRequest: PetRequest): Response<PetResponse>

    @GET("pet/index/{id}")
    suspend fun getPetById(@Path("id") id: String) : Response<Pet>


}