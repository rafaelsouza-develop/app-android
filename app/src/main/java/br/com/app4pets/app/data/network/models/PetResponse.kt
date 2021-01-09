package br.com.app4pets.app.data.network.models

import br.com.app4pets.app.models.Pet
import br.com.app4pets.app.models.User
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

class PetResponse(

    val items: ArrayList<Pet>

)