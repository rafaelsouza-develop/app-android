package br.com.app4pets.app.util.validatehelper

import android.util.Patterns
import java.util.regex.Pattern

object ValidateHelperImpl {

     fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

     fun cpfMatches(cpf: String): Boolean {
        val myPattern = "[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}"
        val p = Pattern.compile(myPattern)
        val m = p.matcher(cpf)
        return m.matches()
    }
}