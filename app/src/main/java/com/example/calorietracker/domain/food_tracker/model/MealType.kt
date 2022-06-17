package com.example.calorietracker.domain.food_tracker.model



sealed class MealType(val type:String){
    object BreakFast:MealType("breakfast")
    object Lunch:MealType("lunch")
    object Dinner:MealType("dinner")
    object Snacks:MealType("snacks")


    companion object{
        fun fromString(input:String):MealType{
            return when(input){
                "breakfast"-> BreakFast
                "lunch" -> Lunch
                "dinner" -> Dinner
                "snacks" -> Snacks
                else -> BreakFast
            }
        }
    }

}
