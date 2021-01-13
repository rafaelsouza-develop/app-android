package br.com.app4pets.app.data.network

import br.com.app4pets.app.models.Pet
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface PetService {

    @GET("pet/index")
    suspend fun listPets(): Response<ArrayList<Pet>>

    @Multipart
    @POST("pet/create")
    suspend fun createPet(
        @Part thumbnail: MultipartBody.Part,
        @Part("name") name: String,
        @Part("breed") breed: String,
        @Part("dateOfBirth") dateOfBirth: String,
        @Part("color") color: String
    ): Response<Pet>

    @GET("pet/index/{id}")
    suspend fun getPetById(@Path("id") id: String): Response<Pet>


}