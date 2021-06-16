package br.com.app4pets.app.data.network.models

import br.com.app4pets.app.domain.models.User
import com.google.gson.annotations.SerializedName

class RegisterResponse (

    @SerializedName("user")
    var user: User?,

    @SerializedName("error")
    var error: String?

)