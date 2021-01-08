package br.com.app4pets.app.util.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.formatPatternBD(format: String): String? {


    val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())

    return simpleDateFormat.format(this)
}
