package com.example.calorietracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calorietracker.ui.screens.onboardings.*
import com.example.calorietracker.ui.screens.onboardings.age.AgeScreen
import com.example.calorietracker.ui.screens.onboardings.gender.GenderScreen
import com.example.calorietracker.ui.viewModels.NavigationViewModel

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    startDestination:String,
    navigationViewModel: NavigationViewModel
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
            HeightScreen(navController = navController)
        }

        composable(route = Screen.AgeScreen.route){
            AgeScreen(navController)
        }

        composable(route = Screen.ActivityLevelScreen.route){
            ActivityLevelScreen(navController)
        }

        composable(route = Screen.WeightChoiceScreen.route){
            WeightChoiceScreen(navController,navigationViewModel)
        }
    }
}