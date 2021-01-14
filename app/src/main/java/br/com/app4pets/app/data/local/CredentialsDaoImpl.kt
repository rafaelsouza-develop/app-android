package br.com.app4pets.app.data.local

import android.content.Context

class CredentialsDaoImpl(context: Context) : CredentialsDao {

    private val sharedPreference = context.getSharedPreferences(
        CREDENTIAL_PREFERENCE_NAME,
        Context.MODE_PRIVATE
    )

    override suspend fun saveToken(token: String) {
        val edit = sharedPreference.edit()
        if (!token.isNullOrEmpty()) {
            edit.putString(CREDENTIAL_ACCESS_TOKEN_KEY, token)
        }
        edit.apply()
    }

    override suspend fun removeToken(): Boolean {
        return sharedPreference
            .edit()
            .remove(CREDENTIAL_ACCESS_TOKEN_KEY)
            .commit()
    }

    override fun getToken(): String? {
        return sharedPreference
            .getString(CREDENTIAL_ACCESS_TOKEN_KEY, "")
    }

    companion object {
        const val CREDENTIAL_PREFERENCE_NAME = "credential_shared_pref"
        const val CREDENTIAL_ACCESS_TOKEN_KEY = "credential_access_token"
    }


}