package com.example.calorietracker.ui.screens.onboardings.age

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietracker.data.datastore.CalorieTrackerDataStore
import com.example.calorietracker.domain.use_cases.FilterOutDigits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val dataStore: CalorieTrackerDataStore
):ViewModel() {

    private val filterOutDigits = FilterOutDigits()

    var age:MutableState<String> = mutableStateOf("20")
    private set


    fun onAgeChange(age:String){
        if(age.length<=3){
            this.age.value= filterOutDigits(age)
        }
    }

    fun saveToPreference(){
        viewModelScope.launch {
            dataStore.saveAge(Integer.parseInt(age.value))
        }
    }
}
