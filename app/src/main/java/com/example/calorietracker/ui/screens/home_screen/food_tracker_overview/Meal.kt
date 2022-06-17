package com.example.calorietracker.ui.screens.home_screen.food_tracker_overview

import androidx.annotation.DrawableRes
import com.example.calorietracker.R
import com.example.calorietracker.domain.food_tracker.model.MealType

data class Meal(
    val name:String,
    @DrawableRes val drawableRes:Int,
    val carbs:Int=0,
    val protein:Int=0,
    val fat:Int=0,
    val calories:Int=0,
    val toggleExpand:Boolean=false,
    val mealType: MealType

)

val mealsDefault = listOf(
    Meal(
        name = "Breakfast",
        drawableRes = R.drawable.ic_breakfast,
        mealType = MealType.BreakFast
    ),
    Meal(
        name = "Lunch",
        drawableRes = R.drawable.ic_lunch,
        mealType = MealType.Lunch
    ),
    Meal(
        name="Snacks",
        drawableRes = R.drawable.ic_snacks,
        mealType = MealType.Snacks
    ),
    Meal(
        name ="Dinner",
        drawableRes = R.drawable.ic_dinner,
        mealType = MealType.Dinner
    )
)
