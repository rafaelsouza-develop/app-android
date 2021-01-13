package br.com.app4pets.app.data.network

import br.com.app4pets.app.data.network.models.PetRequest
import br.com.app4pets.app.data.network.models.PetResponse
import br.com.app4pets.app.models.Pet
import retrofit2.Response
import retrofit2.http.*

interface PetService {

    @GET("pet/index")
    suspend fun listPets(): Response<ArrayList<Pet>>

    @FormUrlEncoded
    @POST("pet/create")
    suspend fun createPet(
        @Field("thumbnail") thumbnail: String,
        @Field("name") name: String,
        @Field("breed") breed: String,
        @Field("dateOfBirth") dateOfBirth: String,
        @Field("color") color: String
    ): Response<Pet>

    @GET("pet/index/{id}")
    suspend fun getPetById(@Path("id") id: String): Response<Pet>


}