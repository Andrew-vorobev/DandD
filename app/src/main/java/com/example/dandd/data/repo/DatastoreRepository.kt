package com.example.dandd.data.repo

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * @author Andrew
 */
interface DatastoreRepository {
    suspend fun save(sorted: Boolean)

    fun get(): Flow<Boolean>
}


val Context.dataStore by preferencesDataStore(name = "sorting")

object PreferenceKeys {
    val SORT = booleanPreferencesKey("sorted")
}

class DatastoreRepositoryImpl(
    private val context: Context
) : DatastoreRepository {
    override suspend fun save(sorted: Boolean) {
        withContext(Dispatchers.IO) {
            context.dataStore.edit { preferences ->
                preferences[PreferenceKeys.SORT] = sorted
            }
        }
    }

    override fun get(): Flow<Boolean> {
        return context.dataStore.data.map{ prefs ->
            prefs[PreferenceKeys.SORT] ?: false
        }
    }
}