package com.example.calorietracker.ui.navigation

sealed class Screen(val route:String){

    object FirstPage:Screen("FirstPage")
    object GenderPage:Screen("genderPage")
    object AgeScreen:Screen("ageScreen")
    object HeightScreen:Screen("heightScreen")
    object ActivityLevelScreen:Screen("activityLevelScreen")
    object NutrientChoiceScreen:Screen("nutrientChoiceScreen")
    object WeightChoiceScreen:Screen("WeightChoiceScreen")
    object Home:Screen("home")
    object Search:Screen("search")
    object WeightScreen:Screen("weightScreen")



}
