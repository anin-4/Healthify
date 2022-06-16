package com.example.calorietracker.data.remote

import com.example.calorietracker.data.remote.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CalorieApi {

    @GET("cgi/search.pl?search_simple=1&json=1&action=process&fields=product_name,nutriments,image_front_thumb_url")
    suspend fun getFood(
        @Query("search_terms") query:String,
        @Query("page") page:Int,
        @Query("page_size") pageSize:Int
    ): SearchDto

    companion object{
        const val BASE_URL = "https://us.openfoodfacts.org/"
    }
}