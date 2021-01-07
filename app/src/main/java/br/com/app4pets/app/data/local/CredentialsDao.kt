package br.com.app4pets.app.data.local

interface CredentialsDao {

    suspend fun saveToken(token: String)

    suspend fun removeToken() : Boolean

    suspend fun getToken(): String?
}