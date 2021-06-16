package br.com.app4pets.app.domain.models

import com.google.gson.annotations.SerializedName

class User(

    @SerializedName("_id")
    var id: String?,

    @SerializedName("name")
    var name: String?,

    @SerializedName("email")
    var email: String?,

    @SerializedName("birthday")
    var birthday: String?
)