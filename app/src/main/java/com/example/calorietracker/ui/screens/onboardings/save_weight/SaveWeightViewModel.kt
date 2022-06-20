package com.example.calorietracker.ui.screens.onboardings.save_weight

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
class SaveWeightViewModel @Inject constructor(
   private val dataStore: CalorieTrackerDataStore
):ViewModel() {

    private val filterOutDigits = FilterOutDigits()

    var weight:MutableState<String> = mutableStateOf("80")
    private set

    private var _uiEvent: Channel<UIEvents>  = Channel()
    val uiEvents = _uiEvent.receiveAsFlow()

    fun onWeightChange(age:String){
        if(age.length<=4){
            this.weight.value= filterOutDigits(age)
        }
    }


    fun saveToPreference(){
        viewModelScope.launch {
            val weightNumber = weight.value.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UIEvents.ShowSnackBar(
                        "Age cannot be empty"
                    )
                )
                weight.value = ""
                return@launch
            }
            dataStore.saveWeight(weightNumber)
        }
    }

}