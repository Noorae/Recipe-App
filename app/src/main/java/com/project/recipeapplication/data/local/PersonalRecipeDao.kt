package com.project.recipeapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.project.recipeapplication.data.model.database.Ingredient
import com.project.recipeapplication.data.model.database.InstructionStep
import com.project.recipeapplication.data.model.database.PersonalRecipe
import com.project.recipeapplication.data.model.database.RecipeSummary
import com.project.recipeapplication.data.model.database.RecipeWithFullData
import com.project.recipeapplication.data.model.database.Tag

@Dao
interface PersonalRecipeDao {
    //Select summarized list of recipes
    @Query("SELECT id, title, imagePath, mealType, isFavorite FROM recipes")
    suspend fun getAll(): List<RecipeSummary>

    //Add new recipe
    @Insert
    suspend fun insertRecipe(recipe: PersonalRecipe): Long

    //add ingredients list
    @Insert
    suspend fun insertIngredients(ingredients: List<Ingredient>)

    //add instruction step list
    @Insert
    suspend fun insertInstructionSteps(instructionSteps: List<InstructionStep>)

    // add tag list
    @Insert
    suspend fun insertTags(tags: List<Tag>)
    @Delete
    suspend fun deleteRecipe(recipe: PersonalRecipe)

    @Transaction
    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    suspend fun getRecipeWithFullData(recipeId: Int): RecipeWithFullData

    @Query("DELETE FROM recipes WHERE id = :id")
    suspend fun deleteRecipeById(id: Int)
}