package com.project.recipeapplication.data.repository

import android.content.Context
import com.project.recipeapplication.data.api.RetrofitInstance
import com.project.recipeapplication.data.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepository() {

    suspend fun fetchRecipes(
        query: String,
        apiKey: String,
        number : Int,
        instructionsRequired : Boolean
    ) : List<Recipe> {
        return RetrofitInstance.recipeService.searchRecipes(query, apiKey, number, instructionsRequired).results
    }
}