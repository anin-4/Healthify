package com.example.calorietracker.ui.screens.onboardings.activity_level

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietracker.data.datastore.CalorieTrackerDataStore
import com.example.calorietracker.domain.model.ActivityLevel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ActivityLevelViewModel @Inject constructor(
    private val dataStore: CalorieTrackerDataStore
):ViewModel() {

    var selectedActivityLevel = mutableStateOf<ActivityLevel>(ActivityLevel.Medium)
    private set

    fun onActivityLevelSelected(activityLevel: ActivityLevel){
        selectedActivityLevel.value = activityLevel
    }

    fun onNextClick(){
        viewModelScope.launch {
            dataStore.saveActivityLevel(selectedActivityLevel.value)
        }
    }

}