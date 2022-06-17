package com.example.calorietracker.domain.food_tracker.use_cases


import com.example.calorietracker.domain.food_tracker.model.TrackableFood
import com.example.calorietracker.domain.food_tracker.respository.FoodTrackerRepository
import com.example.calorietracker.utils.Resource

class SearchFood(
    private val foodTrackerRepository: FoodTrackerRepository
) {

    suspend operator fun invoke(query: String, page:Int, pageSize:Int):Resource<List<TrackableFood>>{
        if(query.isBlank()){
            return Resource.Success(emptyList())
        }
        return foodTrackerRepository.getSearchResult(query.trim(),page,pageSize)
    }
}