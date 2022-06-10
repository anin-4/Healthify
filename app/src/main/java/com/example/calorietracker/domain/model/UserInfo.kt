package com.example.calorietracker.domain.model

data class UserInfo(
    val gender: Gender,
    val age:Int,
    val weight:Int,
    val height:Int,
    val activityLevel: ActivityLevel,
    val goalType: GoalType,
    val carbRatio:Float,
    val proteinRatio:Float,
    val fatRatio:Float
)
