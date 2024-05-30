package com.project.recipeapplication.data.repository

import com.project.recipeapplication.RecipeApplication
import com.project.recipeapplication.data.api.RetrofitInstance
import com.project.recipeapplication.data.model.api.ApiDetailedRecipe
import com.project.recipeapplication.data.model.api.ApiRecipe
import com.project.recipeapplication.data.model.database.ApiFavoriteRecipe

/**
 * Repository class for managing API recipe data.
 */
class ApiRecipeRepository() {

    /**
     * Fetches a list of recipes matching the provided search query.
     *
     * @param query The search query string.
     * @param apiKey The API key required for making the request.
     * @param number The number of recipes to fetch.
     * @param instructionsRequired Indicates whether the fetched recipes must have instructions.
     * @return A list of [ApiRecipe] objects representing the fetched recipes.
     */
    suspend fun fetchRecipes(
        query: String,
        apiKey: String,
        number : Int,
        instructionsRequired : Boolean
    ) : List<ApiRecipe> {
        return RetrofitInstance.recipeService.searchRecipes(query, apiKey, number, instructionsRequired).results
    }

    /**
     * Fetches details of a specific recipe.
     *
     * @param id The ID of the recipe.
     * @param apiKey The API key required for making the request.
     * @return An [ApiDetailedRecipe] object representing the detailed information of the recipe.
     */
    suspend fun fetchRecipeDetails(
        id: Int,
        apiKey: String
    ) : ApiDetailedRecipe {
        return RetrofitInstance.recipeService.getRecipeDetails(id, apiKey)
    }

    /**
     * Fetches favorite recipes stored locally.
     *
     * @return A list of [ApiFavoriteRecipe] objects representing the favorite recipes.
     */
    suspend fun fetchFavoriteRecipes(): List<ApiFavoriteRecipe> {
        val recipes = RecipeApplication.database.apiRecipeDao().getAll()
        println(recipes)
        return recipes
    }

    /**
     * Adds a recipe to the list of favorite recipes.
     *
     * @param recipe The [ApiFavoriteRecipe] object representing the recipe to be added to favorites.
     */
    suspend fun addRecipeToFavorites(recipe: ApiFavoriteRecipe) {
        RecipeApplication.database.apiRecipeDao().insertRecipe(recipe)
    }

    /**
     * Deletes a recipe from the list of favorite recipes.
     *
     * @param recipe The [ApiFavoriteRecipe] object representing the recipe to be deleted from favorites.
     */
    suspend fun deleteFromFavorites(recipe: ApiFavoriteRecipe) {
        RecipeApplication.database.apiRecipeDao().deleteRecipe(recipe)
    }
}