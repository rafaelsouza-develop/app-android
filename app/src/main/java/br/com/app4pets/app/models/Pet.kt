package br.com.app4pets.app.models

import com.google.gson.annotations.SerializedName
import java.util.*

class Pet(

    @SerializedName("name")
    var name: String?,

    @SerializedName("breed")
    var breed: String?,

    @SerializedName("size")
    var size: String?,

    @SerializedName("thumbnail")
    var thumbnail: String?,

    @SerializedName("genre")
    var genre: String?,

    @SerializedName("dateOfBirth")
    var dateOfBirth: Date?,

    @SerializedName("color")
    var color: String?,

    @SerializedName("species")
    var species: String?


)