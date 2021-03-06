package com.example.calorietracker.ui.screens.onboardings.age

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietracker.data.datastore.CalorieTrackerDataStore
import com.example.calorietracker.domain.on_boarding.use_cases.FilterOutDigits
import com.example.calorietracker.utils.UIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val dataStore: CalorieTrackerDataStore
):ViewModel() {

    private val filterOutDigits = FilterOutDigits()

    var age:MutableState<String> = mutableStateOf("20")
    private set

    private val _uiEvents = Channel<UIEvents>()
    val uiEvents = _uiEvents.receiveAsFlow()


    fun onAgeChange(age:String){
        if(age.length<=3){
            this.age.value= filterOutDigits(age)
        }
    }

    fun saveToPreference(){
        viewModelScope.launch {
            val ageNumber = age.value.toIntOrNull() ?: kotlin.run {
                _uiEvents.send(
                    UIEvents.ShowSnackBar(
                        "Age cannot be empty"
                    )
                )
                age.value = ""
                return@launch
            }
            dataStore.saveAge(ageNumber)
        }
    }
}
