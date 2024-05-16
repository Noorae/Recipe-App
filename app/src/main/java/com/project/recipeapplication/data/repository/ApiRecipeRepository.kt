package com.project.recipeapplication.data.repository

import com.project.recipeapplication.data.api.RetrofitInstance
import com.project.recipeapplication.data.model.ApiRecipe

class ApiRecipeRepository() {

    suspend fun fetchRecipes(
        query: String,
        apiKey: String,
        number : Int,
        instructionsRequired : Boolean
    ) : List<ApiRecipe> {
        return RetrofitInstance.recipeService.searchRecipes(query, apiKey, number, instructionsRequired).results
    }
}