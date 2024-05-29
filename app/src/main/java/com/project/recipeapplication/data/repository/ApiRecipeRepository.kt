package com.project.recipeapplication.data.repository

import com.project.recipeapplication.RecipeApplication
import com.project.recipeapplication.data.api.RetrofitInstance
import com.project.recipeapplication.data.model.api.ApiDetailedRecipe
import com.project.recipeapplication.data.model.api.ApiRecipe
import com.project.recipeapplication.data.model.database.ApiFavoriteRecipe
import com.project.recipeapplication.data.model.database.RecipeSummary

class ApiRecipeRepository() {

    //fetch a list of recipes matching search query
    suspend fun fetchRecipes(
        query: String,
        apiKey: String,
        number : Int,
        instructionsRequired : Boolean
    ) : List<ApiRecipe> {
        return RetrofitInstance.recipeService.searchRecipes(query, apiKey, number, instructionsRequired).results
    }

    //fetch recipe details
    suspend fun fetchRecipeDetails(
        id: Int,
        apiKey: String
    ) : ApiDetailedRecipe {
        return RetrofitInstance.recipeService.getRecipeDetails(id, apiKey)
    }

    //fetch favorite recipes
    suspend fun fetchFavoriteRecipes(): List<ApiFavoriteRecipe> {
        val recipes = RecipeApplication.database.apiRecipeDao().getAll()
        println(recipes)
        return recipes
    }

    suspend fun addRecipeToFavorites(recipe: ApiFavoriteRecipe) {
        RecipeApplication.database.apiRecipeDao().insertRecipe(recipe)
    }

    suspend fun deleteFromFavorites(recipe: ApiFavoriteRecipe) {
        RecipeApplication.database.apiRecipeDao().deleteRecipe(recipe)
    }
}