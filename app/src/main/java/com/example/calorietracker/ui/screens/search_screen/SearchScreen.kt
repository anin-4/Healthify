package com.example.calorietracker.ui.screens.search_screen

import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun SearchScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    mealType:String,
    day:Int,
    month:Int,
    year:Int
) {

    Text(text = "Search Screen")

}