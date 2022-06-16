package com.example.calorietracker.data.local

import androidx.room.*
import com.example.calorietracker.data.local.entity.FoodEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(foodEntity: FoodEntity)

    @Delete
    suspend fun deleteFood(foodEntity: FoodEntity)

    @Query(
        """
            SELECT *
            FROM food_table
            WHERE dayOfMonth=:day AND month = :month AND year = :year
        """
    )
    fun getFoodBasedOnDate(day:Int,month: Int, year:Int): Flow<List<FoodEntity>>
}