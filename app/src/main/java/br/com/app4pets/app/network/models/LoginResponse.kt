package br.com.app4pets.app.network.models

import br.com.app4pets.app.models.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("user")
    var user: User?,

    @SerializedName("token")
    var token: String?


)