package com.example.calorietracker.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Nutriments(
    @SerializedName("carbohydrates_100g")
    val carbohydrates100g: Double,
    @SerializedName("energy-kcal_100g")
    val energyKcal100g: Double,
    @SerializedName("fat_100g")
    val fat100g: Double,
    @SerializedName("proteins_100g")
    val proteins100g: Double
)
