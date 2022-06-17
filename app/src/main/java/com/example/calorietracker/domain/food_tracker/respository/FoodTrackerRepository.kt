package com.example.calorietracker.domain.food_tracker.respository

import com.example.calorietracker.domain.food_tracker.model.TrackableFood
import com.example.calorietracker.domain.food_tracker.model.TrackedFood
import com.example.calorietracker.utils.Resource
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface FoodTrackerRepository {

    suspend fun getSearchResult(
        query:String,
        page:Int,
        pageSize:Int
    ):Resource<List<TrackableFood>>


    suspend fun insertTrackedFood(food: TrackedFood)

    suspend fun deleteTrackedFood(food:TrackedFood)

    fun getGoodsFromDate(localDate: LocalDate) : Flow<List<TrackedFood>>
}