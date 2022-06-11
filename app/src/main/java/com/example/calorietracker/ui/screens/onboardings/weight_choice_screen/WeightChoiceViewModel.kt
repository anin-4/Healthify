package com.example.calorietracker.ui.screens.onboardings.weight_choice_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietracker.data.datastore.CalorieTrackerDataStore
import com.example.calorietracker.domain.model.GoalType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeightChoiceViewModel @Inject constructor(
    private val dataStore: CalorieTrackerDataStore
):ViewModel(){

    var selectedGoalType = mutableStateOf<GoalType>(GoalType.LoseWeight)
        private set

    fun onGoalLevelSelected(goalType: GoalType){
        selectedGoalType.value = goalType
    }

    fun onNextClick(){
        viewModelScope.launch {
            dataStore.saveGoalType(selectedGoalType.value)
        }
    }

}