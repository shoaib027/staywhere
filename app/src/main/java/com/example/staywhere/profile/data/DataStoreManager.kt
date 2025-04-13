package com.example.staywhere.profile.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "user_prefs"

val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

class DataStoreManager(private val context: Context) {

    companion object {
        val AUTH_TOKEN = stringPreferencesKey("auth_token")
    }

    suspend fun saveAuthToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[AUTH_TOKEN] = token
        }
    }

    val authTokenFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[AUTH_TOKEN]
    }

    suspend fun clearAuthToken() {
        context.dataStore.edit { prefs ->
            prefs.remove(AUTH_TOKEN)
        }
    }
}
