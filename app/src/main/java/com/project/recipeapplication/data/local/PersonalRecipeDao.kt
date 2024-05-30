package com.project.recipeapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.project.recipeapplication.data.model.database.Ingredient
import com.project.recipeapplication.data.model.database.InstructionStep
import com.project.recipeapplication.data.model.database.PersonalRecipe
import com.project.recipeapplication.data.model.database.RecipeSummary
import com.project.recipeapplication.data.model.database.RecipeWithFullData
import com.project.recipeapplication.data.model.database.ShoppingItem
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

    // delete recipe
    @Transaction
    @Query("DELETE FROM recipes WHERE id = :id")
    suspend fun deleteRecipeById(id: Int)

    @Query("DELETE FROM ingredients WHERE recipeId = :recipeId")
    suspend fun deleteIngredientsByRecipeId(recipeId: Int)

    @Query("DELETE FROM instruction_steps WHERE recipeId = :recipeId")
    suspend fun deleteInstructionStepsByRecipeId(recipeId: Int)

    @Query("DELETE FROM tags WHERE recipeId = :recipeId")
    suspend fun deleteTagsByRecipeId(recipeId: Int)

    //get recipe with full data
    @Transaction
    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    suspend fun getRecipeWithFullData(recipeId: Int): RecipeWithFullData

    @Query("SELECT id, name, itemChecked FROM shopping_items")
    suspend fun getShoppingList(): List<ShoppingItem>

    //get random recipe with full data
    @Transaction
    @Query("SELECT * FROM recipes ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomRecipe(): RecipeWithFullData

    // newest recipe with full data
    @Transaction
    @Query("SELECT * FROM recipes ORDER BY id DESC LIMIT 1")
    suspend fun getNewestRecipe(): RecipeWithFullData

    //Add new shopping item
    @Insert
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Update
    suspend fun updateShoppingItem(shoppingItem: ShoppingItem)

    @Delete
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

}