package com.example.calorietracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calorietracker.data.local.entity.FoodEntity


@Database(
    entities = [FoodEntity::class],
    version = 1
)
abstract class FoodDatabase:RoomDatabase() {

    abstract val dao:FoodDao
}