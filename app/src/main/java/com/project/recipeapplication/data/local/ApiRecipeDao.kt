package com.project.recipeapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.project.recipeapplication.data.model.database.ApiFavoriteRecipe


@Dao
interface ApiRecipeDao {

    //Select summarized list of recipes
    @Query("SELECT id, apiId, title, imagePath FROM api_recipes")
    suspend fun getAll(): List<ApiFavoriteRecipe>

    @Delete
    suspend fun deleteRecipe(recipe: ApiFavoriteRecipe)

    @Insert
    suspend fun insertRecipe(recipe: ApiFavoriteRecipe)
}