package com.project.recipeapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.project.recipeapplication.data.model.PersonalRecipe

@Dao
interface PersonalRecipeDao {
    @Query("SELECT * FROM recipes")
    suspend fun getAll(): List<PersonalRecipe>

    @Upsert
    suspend fun upsertRecipe(recipe: PersonalRecipe)

    @Delete
    suspend fun deleteRecipe(recipe: PersonalRecipe)


}