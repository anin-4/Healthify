package com.example.calorietracker.ui.screens.home_screen


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.FoodTrackerViewModel
import com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.TrackerOverViewEvents
import com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.components.DaySelector
import com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.components.Header


@Composable
fun HomeScreen(
    viewModel: FoodTrackerViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.state.value


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 12.dp)
    ){
        item {
            Header(state = state)
            Spacer(modifier = Modifier.height(20.dp))

            DaySelector(
                date = state.date,
                onNextDayClicked = {viewModel.onEvent(TrackerOverViewEvents.OnNextDayClicked)},
                onPrevDayClicked = {viewModel.onEvent(TrackerOverViewEvents.OnPrevDayClicked)}
            )
            
            Spacer(modifier = Modifier.height(20.dp))
        }

        
    }

}