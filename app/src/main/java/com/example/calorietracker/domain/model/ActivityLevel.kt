package com.example.calorietracker.domain.model

sealed class ActivityLevel(val name:String){

    object Low:ActivityLevel("low")
    object Medium:ActivityLevel("medium")
    object High:ActivityLevel("High")


    companion object{
        fun fromString(activityLevel: String):ActivityLevel{
            return when(activityLevel){
                "low" -> Low
                "medium"-> Medium
                "high" -> High
                else -> Medium
            }
        }
    }

}
