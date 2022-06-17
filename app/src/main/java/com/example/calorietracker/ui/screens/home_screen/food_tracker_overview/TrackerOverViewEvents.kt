package com.example.calorietracker.ui.screens.home_screen.food_tracker_overview

import com.example.calorietracker.domain.food_tracker.model.TrackedFood

sealed class TrackerOverViewEvents{
    object OnNextDayClicked:TrackerOverViewEvents()
    object OnPrevDayClicked:TrackerOverViewEvents()
    data class OnToggleButtonClicked(val meal:Meal):TrackerOverViewEvents()
    data class OnDeleteTrackedFoodButtonClicked(val trackedFood: TrackedFood):TrackerOverViewEvents()
    data class OnAddFoodButtonClicked(val meal:Meal):TrackerOverViewEvents()
}
