package com.example.calorietracker.ui.screens.search_screen

data class SearchScreenState(
    val searchQuery:String="",
    val isHintVisible:Boolean = false,
    val isLoading:Boolean = false,
    val trackableFood:List<TrackableFoodState> = emptyList()
)
