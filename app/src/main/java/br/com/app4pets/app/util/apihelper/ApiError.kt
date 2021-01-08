package br.com.app4pets.app.util.apihelper

import com.google.gson.annotations.SerializedName

open class ApiError(
    @SerializedName("title")
    var title: String? = null,

    @SerializedName("message")
    var message: String? = null
)