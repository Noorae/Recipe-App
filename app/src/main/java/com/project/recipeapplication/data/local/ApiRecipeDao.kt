package com.project.recipeapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.project.recipeapplication.data.model.database.ApiFavoriteRecipe

/**
 * Data Access Object (DAO) for accessing and managing API recipe data in the local database.
 */
@Dao
interface ApiRecipeDao {

    /**
     * Retrieves a summarized list of all API favorite recipes from the local database.
     *
     * @return A list of [ApiFavoriteRecipe] objects representing the summarized list of recipes.
     */
    @Query("SELECT id, apiId, title, imagePath FROM api_recipes")
    suspend fun getAll(): List<ApiFavoriteRecipe>

    /**
     * Deletes a specified API favorite recipe from the local database.
     *
     * @param recipe The [ApiFavoriteRecipe] to be deleted.
     */
    @Delete
    suspend fun deleteRecipe(recipe: ApiFavoriteRecipe)

    /**
     * Inserts a new API favorite recipe into the local database.
     *
     * @param recipe The [ApiFavoriteRecipe] to be inserted.
     */
    @Insert
    suspend fun insertRecipe(recipe: ApiFavoriteRecipe)
}