package com.example.calorietracker.ui.screens.search_screen

import com.example.calorietracker.domain.food_tracker.model.TrackableFood

data class TrackableFoodState(
    val amount:String ="",
    val isExpanded:Boolean = false,
    val food:TrackableFood
)
