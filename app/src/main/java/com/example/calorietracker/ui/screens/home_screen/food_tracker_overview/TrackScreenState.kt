package com.example.calorietracker.ui.screens.home_screen.food_tracker_overview

import com.example.calorietracker.domain.food_tracker.model.TrackedFood
import java.time.LocalDate

data class TrackScreenState(
    val currentCalorieCount:Int=0,
    val currentCarbCount:Int=0,
    val currentProteinCount:Int=0,
    val currentFatCount:Int=0,
    val date:LocalDate = LocalDate.now(),
    val meals:List<Meal> = mealsDefault,
    val trackedFoods:List<TrackedFood> = emptyList(),
    val calorieGoal:Int=0,
    val carbGoal:Int=0,
    val proteinGoal:Int=0,
    val fatGoal:Int=0
)
