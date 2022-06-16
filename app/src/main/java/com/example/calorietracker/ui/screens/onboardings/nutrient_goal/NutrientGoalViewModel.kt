package com.example.calorietracker.ui.screens.onboardings.nutrient_goal

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietracker.data.datastore.CalorieTrackerDataStore
import com.example.calorietracker.domain.on_boarding.use_cases.FilterOutDigits
import com.example.calorietracker.domain.on_boarding.use_cases.NutrientResult
import com.example.calorietracker.domain.on_boarding.use_cases.ValidateNutrients
import com.example.calorietracker.utils.UIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val dataStore: CalorieTrackerDataStore
): ViewModel(){

    private val filterOutDigits = FilterOutDigits()
    private val validateNutrients = ValidateNutrients()

    var nutrient: MutableState<NutrientState> = mutableStateOf(NutrientState())
    private set

    var navigate = mutableStateOf(false)
    private set

    private var _uiEvent = Channel<UIEvents>()
    val uiEvents = _uiEvent.receiveAsFlow()


    fun onEnter(event:NutrientGoalEvent){
        when(event){
            is NutrientGoalEvent.OnCarbEnter -> {
                nutrient.value = nutrient.value.copy(
                    carbRatio = filterOutDigits(event.ratio)
                )
            }

            is NutrientGoalEvent.OnFatEnter -> {
                nutrient.value = nutrient.value.copy(
                    fatRatio = filterOutDigits(event.ratio)
                )
            }

            is NutrientGoalEvent.OnProteinEnter -> {
                nutrient.value = nutrient.value.copy(
                    proteinRatio = filterOutDigits(event.ratio)
                )
            }

            is NutrientGoalEvent.OnNextClicked -> {
                val result = validateNutrients(
                    carbRatioText = nutrient.value.carbRatio,
                    fatRatioText = nutrient.value.fatRatio,
                    proteinRatioText = nutrient.value.proteinRatio
                )

                when(result){
                    is NutrientResult.Success->{
                        viewModelScope.launch {
                            dataStore.saveCarbRatio(result.carbRatio)
                            dataStore.saveProteinRatio(result.proteinRatio)
                            dataStore.saveFatRatio(result.fatRatio)
                        }
                        navigate.value=true
                    }

                    is NutrientResult.Error->{
                        viewModelScope.launch {
                            _uiEvent.send(UIEvents.ShowSnackBar(result.msg))
                        }
                        navigate.value=false
                    }
                }
            }
        }
    }

}