package com.example.calorietracker.domain.food_tracker.use_cases

import com.example.calorietracker.domain.food_tracker.model.TrackedFood
import com.example.calorietracker.domain.food_tracker.respository.FoodTrackerRepository

class DeleteTrackedFood(
    private val foodTrackerRepository: FoodTrackerRepository
) {
    suspend operator fun invoke(trackedFood: TrackedFood){
        foodTrackerRepository.deleteTrackedFood(trackedFood)
    }
}