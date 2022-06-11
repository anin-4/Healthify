package com.example.calorietracker.ui.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calorietracker.ui.screens.home_screen.HomeScreen
import com.example.calorietracker.ui.screens.onboardings.*
import com.example.calorietracker.ui.screens.onboardings.activity_level.ActivityLevelScreen
import com.example.calorietracker.ui.screens.onboardings.age.AgeScreen
import com.example.calorietracker.ui.screens.onboardings.gender.GenderScreen
import com.example.calorietracker.ui.screens.onboardings.height.HeightScreen
import com.example.calorietracker.ui.screens.onboardings.weight_choice_screen.WeightChoiceScreen
import com.example.calorietracker.ui.viewModels.NavigationViewModel

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    startDestination:String,
    navigationViewModel: NavigationViewModel = hiltViewModel(),
    scaffoldState:ScaffoldState
)
{
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.FirstPage.route) {
            FirstPageOnBoarding(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen()
        }

        composable(route = Screen.GenderPage.route){
            GenderScreen(navController= navController)
        }

        composable(route = Screen.HeightScreen.route){
            HeightScreen(navController = navController, scaffoldState= scaffoldState)
        }

        composable(route = Screen.AgeScreen.route){
            AgeScreen(navController,scaffoldState=scaffoldState)
        }

        composable(route = Screen.ActivityLevelScreen.route){
            ActivityLevelScreen(navController)
        }

        composable(route = Screen.WeightChoiceScreen.route){
            WeightChoiceScreen(navController,navigationViewModel)
        }
    }
}