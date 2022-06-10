package com.example.calorietracker.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.calorietracker.domain.model.ActivityLevel
import com.example.calorietracker.domain.model.Gender
import com.example.calorietracker.domain.model.GoalType
import com.example.calorietracker.domain.model.UserInfo
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

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preference")

    private val dataStore = context.dataStore

    private object PreferenceKeys{
        val KEY_ON_BOARDING = booleanPreferencesKey(name = "on_boarding_done")
        val KEY_GENDER = stringPreferencesKey(name ="gender")
        val KEY_AGE = intPreferencesKey(name="age")
        val KEY_WEIGHT = intPreferencesKey(name = "weight")
        val KEY_HEIGHT = intPreferencesKey(name= "height")
        val KEY_ACTIVITY_LEVEL = stringPreferencesKey(name="activityLevel")
        val KEY_GOAL_TYPE = stringPreferencesKey(name ="goalType")
        val KEY_CARB_RATIO = floatPreferencesKey(name="carbRatio")
        val KEY_PROTEIN_RATIO = floatPreferencesKey(name="proteinRatio")
        val KEY_FAT_RATIO = floatPreferencesKey(name="fatRatio")
    }

    suspend fun saveOnBoardingState(completed:Boolean){
        dataStore.edit { preferences->
            preferences[PreferenceKeys.KEY_ON_BOARDING]=completed
        }
    }

    suspend fun saveGender(gender: Gender){
        dataStore.edit { preferences->
            preferences[PreferenceKeys.KEY_GENDER]= gender.name

        }
    }

    suspend fun saveActivityLevel(activityLevel: ActivityLevel){
        dataStore.edit { preferences->
            preferences[PreferenceKeys.KEY_ACTIVITY_LEVEL]=activityLevel.name
        }
    }

    suspend fun saveGoalType(goalType: GoalType){
        dataStore.edit { preferences->
            preferences[PreferenceKeys.KEY_GOAL_TYPE]= goalType.name
        }
    }

    suspend fun saveAge(age:Int){
        dataStore.edit { preferences->
            preferences[PreferenceKeys.KEY_AGE]=age
        }
    }

    suspend fun saveHeight(height:Int){
        dataStore.edit { preferences->
            preferences[PreferenceKeys.KEY_HEIGHT]= height
        }
    }

    suspend fun saveWeight(weight:Int){
        dataStore.edit { preferences->
            preferences[PreferenceKeys.KEY_WEIGHT]= weight
        }
    }

    suspend fun saveProteinRatio(proteinRatio:Float){
        dataStore.edit { preferences->
            preferences[PreferenceKeys.KEY_PROTEIN_RATIO]=proteinRatio
        }
    }

    suspend fun saveFatRatio(fatRatio:Float){
        dataStore.edit { preferences->
            preferences[PreferenceKeys.KEY_FAT_RATIO]= fatRatio
        }
    }

    suspend fun saveCarbRatio(carbRatio:Float){
        dataStore.edit { preferences->
            preferences[PreferenceKeys.KEY_CARB_RATIO]=carbRatio
        }
    }

    fun readUserInfo():Flow<UserInfo>{
        return dataStore.data
            .map { preferences ->
                val age = preferences[PreferenceKeys.KEY_AGE]?:-1
                val genderString = preferences[PreferenceKeys.KEY_GENDER]?:null
                val height = preferences[PreferenceKeys.KEY_HEIGHT]?:-1
                val weight = preferences[PreferenceKeys.KEY_WEIGHT]?:-1
                val goalTypeString = preferences[PreferenceKeys.KEY_GOAL_TYPE]?:null
                val carbRatio = preferences[PreferenceKeys.KEY_CARB_RATIO]?:-1f
                val proteinRatio = preferences[PreferenceKeys.KEY_PROTEIN_RATIO]?:-1f
                val fatRatio = preferences[PreferenceKeys.KEY_FAT_RATIO]?:-1f
                val activityLevelString = preferences[PreferenceKeys.KEY_ACTIVITY_LEVEL]?:null
                UserInfo(
                    age = age,
                    gender = Gender.fromString(genderString?: "male"),
                    height = height,
                    weight = weight,
                    goalType = GoalType.fromString(goalTypeString?:"keep_weight"),
                    activityLevel = ActivityLevel.fromString(activityLevelString?:"medium"),
                    carbRatio = carbRatio,
                    proteinRatio = proteinRatio,
                    fatRatio = fatRatio
                )
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
                val onBoardingState = preferences[PreferenceKeys.KEY_ON_BOARDING] ?: false
                onBoardingState
            }
    }


}