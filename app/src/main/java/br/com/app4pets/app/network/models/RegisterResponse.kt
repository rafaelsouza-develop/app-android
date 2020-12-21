package br.com.app4pets.app.network.models

import br.com.app4pets.app.models.User
import com.google.gson.annotations.SerializedName

class RegisterResponse (

    @SerializedName("user")
    var user: User?

)