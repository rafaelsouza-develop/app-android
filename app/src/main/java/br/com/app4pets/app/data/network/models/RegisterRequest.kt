package br.com.app4pets.app.data.network.models

import java.util.*

class RegisterRequest(

    var name: String,
    var email: String,
    var password: String,
    var birthday: Date
)