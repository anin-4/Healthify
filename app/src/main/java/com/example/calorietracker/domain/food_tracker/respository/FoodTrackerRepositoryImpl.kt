package com.example.calorietracker.domain.food_tracker.respository

import com.example.calorietracker.data.local.FoodDatabase
import com.example.calorietracker.data.mapper.toTrackableFood
import com.example.calorietracker.data.mapper.toTrackedFood
import com.example.calorietracker.data.mapper.toTrackedFoodEntity
import com.example.calorietracker.data.remote.CalorieApi
import com.example.calorietracker.domain.food_tracker.model.TrackableFood
import com.example.calorietracker.domain.food_tracker.model.TrackedFood
import com.example.calorietracker.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate


class FoodTrackerRepositoryImpl(
    private val api:CalorieApi,
    private val foodDatabase:FoodDatabase
):FoodTrackerRepository {

    override suspend fun getSearchResult(
        query: String,
        page: Int,
        pageSize: Int
    ): Resource<List<TrackableFood>> {
        return try{
            val searchDto = api.getFood(query,page,pageSize)

            Resource.Success(
                data = searchDto.products.mapNotNull {
                    it.toTrackableFood()
                }
            )
        }catch(e:Exception) {
            e.printStackTrace()
            Resource.Error(
                error = e.localizedMessage?:"A error occurred"
            )
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        foodDatabase.dao.insertFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        foodDatabase.dao.deleteFood(food.toTrackedFoodEntity())
    }

    override fun getGoodsFromDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return foodDatabase.dao.getFoodBasedOnDate(
            day = localDate.dayOfMonth,
            month =  localDate.monthValue,
            year = localDate.year
        ).map { entities->
            entities.map {
                it.toTrackedFood()
        }
        }
    }
}