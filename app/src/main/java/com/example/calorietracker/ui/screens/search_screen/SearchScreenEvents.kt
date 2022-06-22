package com.example.calorietracker.ui.screens.search_screen

import com.example.calorietracker.domain.food_tracker.model.MealType
import com.example.calorietracker.domain.food_tracker.model.TrackableFood
import java.time.LocalDate

sealed class SearchScreenEvents{

    data class OnQueryChange(val query:String):SearchScreenEvents()
    object OnSearch:SearchScreenEvents()
    data class OnToggleTrackableFood(val food: TrackableFood):SearchScreenEvents()
    data class OnAmountChangeForFood(val food:TrackableFood, val amount:String):SearchScreenEvents()
    data class OnTrackFoodClicked(val food:TrackableFood, val mealTypes: MealType, val date:LocalDate):SearchScreenEvents()
    data class OnSearchFocusChange(val isFocused:Boolean):SearchScreenEvents()
}
