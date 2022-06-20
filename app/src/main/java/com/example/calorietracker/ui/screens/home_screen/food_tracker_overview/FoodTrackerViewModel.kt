package com.example.calorietracker.ui.screens.home_screen.food_tracker_overview

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietracker.domain.food_tracker.use_cases.TrackFoodUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FoodTrackerViewModel @Inject constructor(
    private val useCases: TrackFoodUseCases
):ViewModel(){

    var state:MutableState<TrackScreenState> = mutableStateOf(TrackScreenState())
    private set

    private var getFoodsForDateJob: Job?=null

    fun onEvent(event:TrackerOverViewEvents){
        when(event){
            is TrackerOverViewEvents.OnNextDayClicked -> {
                state.value = state.value.copy(
                    date = state.value.date.plusDays(1)
                )
                refreshFood()
            }

            is TrackerOverViewEvents.OnPrevDayClicked -> {
                state.value = state.value.copy(
                    date = state.value.date.minusDays(1)
                )
                refreshFood()
            }

            is TrackerOverViewEvents.OnDeleteTrackedFoodButtonClicked->{
                viewModelScope.launch {
                    useCases.deleteTrackedFood(event.trackedFood)
                    refreshFood()
                }
            }

            is TrackerOverViewEvents.OnToggleButtonClicked -> {
                state.value = state.value.copy(
                    meals = state.value.meals.map {
                        if(it.name == event.meal.name){
                            it.copy(toggleExpand = !it.toggleExpand)
                        }else it
                    }
                )
            }
        }
    }

    private fun refreshFood() {
        getFoodsForDateJob?.cancel()

        getFoodsForDateJob = viewModelScope.launch {
            useCases.getFoodFromDate(state.value.date)
                .onEach {
                   val nutrientResult = useCases.calculateMealNutrients(it)
                    state.value = state.value.copy(
                        currentCalorieCount = nutrientResult.totalCalories,
                        currentCarbCount =  nutrientResult.totalCarbs,
                        currentFatCount = nutrientResult.totalFat,
                        currentProteinCount = nutrientResult.totalProtein,
                        calorieGoal = nutrientResult.caloriesGoal,
                        fatGoal = nutrientResult.fatGoal,
                        proteinGoal = nutrientResult.proteinGoal,
                        carbGoal = nutrientResult.carbsGoal,
                        trackedFoods = it,
                        meals = state.value.meals.map {meal->
                            val nutrientForMeal = nutrientResult.mealNutrients[meal.mealType]?: return@map meal.copy(
                                calories = 0,
                                carbs = 0,
                                fat =0,
                                protein = 0
                            )
                            meal.copy(
                                calories = nutrientForMeal.calories,
                                carbs = nutrientForMeal.carbs,
                                fat = nutrientForMeal.fat,
                                protein = nutrientForMeal.protein
                            )
                        }
                    )
                }
        }
    }


}