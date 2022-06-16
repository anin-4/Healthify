package com.example.calorietracker.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("image_front_thumb_url")
    val imageFrontThumbUrl: String?,
    val nutriments: Nutriments,
    @SerializedName("product_name")
    val productName: String?
)
