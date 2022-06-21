package com.example.calorietracker.ui.screens.home_screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.FoodTrackerViewModel
import com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.TrackerOverViewEvents
import com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.components.DaySelector
import com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.components.ExpandableMealItem
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

        items(state.meals){meal->
            ExpandableMealItem(
                comp = {/*TODO*/ },
                onToggleClick = {viewModel.onEvent(TrackerOverViewEvents.OnToggleButtonClicked(meal))},
                meal = meal,
                modifier = Modifier.fillMaxWidth()

            )

        }


    }

}