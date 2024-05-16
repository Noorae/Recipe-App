package com.project.recipeapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.project.recipeapplication.data.model.PersonalRecipe

@Dao
interface PersonalRecipeDao {
    @Query("SELECT * FROM recipes")
    suspend fun getAll(): List<PersonalRecipe>
}