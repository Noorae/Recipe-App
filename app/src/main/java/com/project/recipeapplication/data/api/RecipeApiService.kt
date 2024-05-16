package com.project.recipeapplication.data.api

import com.project.recipeapplication.data.model.ApiResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiService {
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int,
        @Query("instructionsRequired") instructionsRequired: Boolean
    ): ApiResponseData
}