package com.example.routeapp.data
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager(private val context: Context) {

    companion object {
        val KEY_TOKEN = stringPreferencesKey("token")
        val KEY_IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    val userToken: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[KEY_TOKEN]
    }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_IS_LOGGED_IN] ?: false
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_TOKEN] = token
            prefs[KEY_IS_LOGGED_IN] = true
        }
    }

    suspend fun clearToken() {
        context.dataStore.edit { prefs ->
            prefs.remove(KEY_TOKEN)
            prefs[KEY_IS_LOGGED_IN] = false
        }
    }
}
