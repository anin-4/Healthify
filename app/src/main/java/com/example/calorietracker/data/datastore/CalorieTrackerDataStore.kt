package com.example.calorietracker.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CalorieTrackerDataStore @Inject constructor(
    @ApplicationContext context: Context
) {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preference")

    val dataStore = context.dataStore

    private object PreferenceKeys{
        val onBoardingKey = booleanPreferencesKey(name = "on_boarding_done")

    }

    suspend fun saveOnBoardingState(completed:Boolean){
        dataStore.edit { preferences->
            preferences[PreferenceKeys.onBoardingKey]=completed
        }
    }

    fun readOnBoardingState():Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferenceKeys.onBoardingKey] ?: false
                onBoardingState
            }
    }


}