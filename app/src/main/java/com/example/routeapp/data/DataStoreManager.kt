package com.example.routeapp.data
import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")

class DataStoreManager(private val context: Context) {

    private val TOKEN_KEY = stringPreferencesKey("token")

    suspend fun saveToken(token: String) {
        context.dataStore.edit {
            it[TOKEN_KEY] = token
        }
    }

    fun getToken() = context.dataStore.data.map {
        it[TOKEN_KEY]
    }
}
