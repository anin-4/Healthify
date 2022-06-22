package com.example.calorietracker.ui.screens.home_screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.calorietracker.ui.navigation.Screen
import com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.FoodTrackerViewModel
import com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.TrackerOverViewEvents
import com.example.calorietracker.ui.screens.home_screen.food_tracker_overview.components.*


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
                comp = {
                       Column(modifier= Modifier
                           .fillMaxWidth()
                           .padding(8.dp),
                           horizontalAlignment = Alignment.CenterHorizontally
                       ) {

                           state.trackedFoods.forEach {
                               if(it.mealType== meal.mealType) {
                                   TrackedFoodItem(trackedFood = it) {
                                       viewModel.onEvent(
                                           TrackerOverViewEvents.OnDeleteTrackedFoodButtonClicked(it)
                                       )

                                   }
                               }

                               Spacer(modifier = Modifier.height(8.dp))
                           }

                           AddMealButton(name = meal.mealType.type) {
                               navController.navigate(
                                   Screen.Search.route
                                           + "/${meal.mealType.type}"
                                           + "/${state.date.dayOfMonth.toInt()}"
                                           + "/${state.date.month.value.toInt()}"
                                           + "/${state.date.year.toInt()}"
                               )
                           }
                           
                       }

                },
                onToggleClick = {viewModel.onEvent(TrackerOverViewEvents.OnToggleButtonClicked(meal))},
                meal = meal,
                modifier = Modifier.fillMaxWidth()

            )

        }


    }

}