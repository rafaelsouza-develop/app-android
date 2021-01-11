package br.com.app4pets.app.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
open class Pet(

    @SerializedName("_id")
    var id: String?,

    @SerializedName("genre")
    var genre: String?,

    @SerializedName("name")
    var name: String?,

    @SerializedName("breed")
    var breed: String?,

    @SerializedName("thumbnail")
    var thumbnail: String?,

    @SerializedName("dateOfBirth")
    var dateOfBirth: Date?

): Parcelable