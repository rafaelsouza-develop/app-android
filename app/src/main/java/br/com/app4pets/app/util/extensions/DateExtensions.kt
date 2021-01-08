package br.com.app4pets.app.util.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.toString(format: String): String? {

    val locale = Locale.getDefault()
    val simpleDateFormat = SimpleDateFormat(format, locale)

    return simpleDateFormat.format(this)
}