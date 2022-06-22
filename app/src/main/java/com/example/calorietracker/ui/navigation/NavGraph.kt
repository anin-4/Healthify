package com.example.calorietracker.ui.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.calorietracker.ui.screens.home_screen.HomeScreen
import com.example.calorietracker.ui.screens.onboardings.*
import com.example.calorietracker.ui.screens.onboardings.activity_level.ActivityLevelScreen
import com.example.calorietracker.ui.screens.onboardings.age.AgeScreen
import com.example.calorietracker.ui.screens.onboardings.gender.GenderScreen
import com.example.calorietracker.ui.screens.onboardings.height.HeightScreen
import com.example.calorietracker.ui.screens.onboardings.nutrient_goal.NutrientGoalScreen
import com.example.calorietracker.ui.screens.onboardings.save_weight.SaveWeightScreen
import com.example.calorietracker.ui.screens.onboardings.weight_choice_screen.WeightChoiceScreen
import com.example.calorietracker.ui.screens.search_screen.SearchScreen

@OptIn(ExperimentalComposeUiApi::class)
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
            HomeScreen(navController=navController)
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

        composable(route = Screen.NutrientChoiceScreen.route){
            NutrientGoalScreen(scaffoldState = scaffoldState, navController = navController)
        }

        composable(route = Screen.WeightChoiceScreen.route){
            WeightChoiceScreen(navController,navigationViewModel)
        }

        composable(
            route = Screen.Search.route + "/{mealType}/{day}/{month}/{year}",
            arguments = listOf(
                navArgument("mealType"){type = NavType.StringType},
                navArgument(name ="day"){type = NavType.IntType},
                navArgument(name="month"){type= NavType.IntType},
                navArgument(name="year"){type= NavType.IntType}
            )
        ){
            SearchScreen(
                navController,
                scaffoldState,
                it.arguments?.getString("mealType")?:"",
                it.arguments?.getInt("day")?:-1,
                it.arguments?.getInt("month")?:-1,
                it.arguments?.getInt("year")?:-1

            )
        }

        composable(route = Screen.WeightScreen.route){
            SaveWeightScreen(scaffoldState = scaffoldState, navController = navController)
        }
    }
}