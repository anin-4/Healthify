package com.example.calorietracker.ui.screens.onboardings.nutrient_goal



sealed class NutrientGoalEvent{
    data class OnCarbEnter(val ratio:String):NutrientGoalEvent()
    data class OnProteinEnter(val ratio:String):NutrientGoalEvent()
    data class OnFatEnter(val ratio:String):NutrientGoalEvent()
    object OnNextClicked:NutrientGoalEvent()
}
