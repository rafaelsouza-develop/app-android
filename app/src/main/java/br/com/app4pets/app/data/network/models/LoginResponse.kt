package br.com.app4pets.app.data.network.models

import br.com.app4pets.app.domain.models.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("user")
    var user: User?,

    @SerializedName("token")
    var token: String?,

    @SerializedName("error")
    var error: String?
)