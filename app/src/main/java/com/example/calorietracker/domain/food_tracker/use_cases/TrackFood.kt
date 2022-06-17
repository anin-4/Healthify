package com.example.calorietracker.domain.food_tracker.use_cases

import com.example.calorietracker.domain.food_tracker.model.MealType
import com.example.calorietracker.domain.food_tracker.model.TrackableFood
import com.example.calorietracker.domain.food_tracker.model.TrackedFood
import com.example.calorietracker.domain.food_tracker.respository.FoodTrackerRepository
import java.time.LocalDate

class TrackFood(
    private val foodTrackerRepository: FoodTrackerRepository
) {

    suspend operator fun invoke(
        food:TrackableFood,
        mealType: MealType,
        amount:Int,
        date:LocalDate
    ){
        foodTrackerRepository.insertTrackedFood(
            food = TrackedFood(
                name = food.name,
                carbs = ((food.carbsPer100g/100f)*amount).toInt(),
                protein = ((food.proteinPer100g/100f)*amount).toInt(),
                fat = ((food.fatPer100g/100f)*amount).toInt(),
                calories = ((food.caloriesPer100g/100f)*amount).toInt(),
                imageUrl = food.imageUrl,
                mealType = mealType,
                date = date,
                amount = amount
            )
        )
    }
}