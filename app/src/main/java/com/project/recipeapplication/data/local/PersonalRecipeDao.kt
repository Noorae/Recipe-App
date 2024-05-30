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

/**
 * Data Access Object (DAO) for accessing and managing personal recipe data in the local database.
 */
@Dao
interface PersonalRecipeDao {

    /**
     * Retrieves a summarized list of all personal recipes from the local database.
     *
     * @return A list of [RecipeSummary] objects representing the summarized list of recipes.
     */
    @Query("SELECT id, title, imagePath, mealType, isFavorite FROM recipes")
    suspend fun getAll(): List<RecipeSummary>

    /**
     * Inserts a new personal recipe into the local database.
     *
     * @param recipe The [PersonalRecipe] to be inserted.
     * @return The ID of the newly inserted recipe.
     */
    @Insert
    suspend fun insertRecipe(recipe: PersonalRecipe): Long

    /**
     * Inserts a list of ingredients into the local database.
     *
     * @param ingredients The list of [Ingredient] objects to be inserted.
     */
    @Insert
    suspend fun insertIngredients(ingredients: List<Ingredient>)

    /**
     * Inserts a list of instruction steps into the local database.
     *
     * @param instructionSteps The list of [InstructionStep] objects to be inserted.
     */
    @Insert
    suspend fun insertInstructionSteps(instructionSteps: List<InstructionStep>)

    /**
     * Inserts a list of tags into the local database.
     *
     * @param tags The list of [Tag] objects to be inserted.
     */
    @Insert
    suspend fun insertTags(tags: List<Tag>)

    /**
     * Deletes a recipe from the local database by its ID.
     *
     * @param id The ID of the recipe to be deleted.
     */
    @Transaction
    @Query("DELETE FROM recipes WHERE id = :id")
    suspend fun deleteRecipeById(id: Int)

    /**
     * Deletes all ingredients associated with a recipe from the local database.
     *
     * @param recipeId The ID of the recipe whose ingredients are to be deleted.
     */
    @Query("DELETE FROM ingredients WHERE recipeId = :recipeId")
    suspend fun deleteIngredientsByRecipeId(recipeId: Int)

    /**
     * Deletes all instruction steps associated with a recipe from the local database.
     *
     * @param recipeId The ID of the recipe whose instruction steps are to be deleted.
     */
    @Query("DELETE FROM instruction_steps WHERE recipeId = :recipeId")
    suspend fun deleteInstructionStepsByRecipeId(recipeId: Int)

    /**
     * Deletes all tags associated with a recipe from the local database.
     *
     * @param recipeId The ID of the recipe whose tags are to be deleted.
     */
    @Query("DELETE FROM tags WHERE recipeId = :recipeId")
    suspend fun deleteTagsByRecipeId(recipeId: Int)

    /**
     * Retrieves a recipe with its full data from the local database.
     *
     * @param recipeId The ID of the recipe to retrieve.
     * @return A [RecipeWithFullData] object representing the recipe with its full data.
     */
    @Transaction
    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    suspend fun getRecipeWithFullData(recipeId: Int): RecipeWithFullData

    /**
     * Retrieves the list of shopping items from the local database.
     *
     * @return A list of [ShoppingItem] objects representing the shopping items.
     */
    @Query("SELECT id, name, itemChecked FROM shopping_items")
    suspend fun getShoppingList(): List<ShoppingItem>

    /**
     * Retrieves a random recipe with its full data from the local database.
     *
     * @return A [RecipeWithFullData] object representing the random recipe.
     */
    @Transaction
    @Query("SELECT * FROM recipes ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomRecipe(): RecipeWithFullData

    /**
     * Retrieves the newest recipe with its full data from the local database.
     *
     * @return A [RecipeWithFullData] object representing the newest recipe.
     */
    @Transaction
    @Query("SELECT * FROM recipes ORDER BY id DESC LIMIT 1")
    suspend fun getNewestRecipe(): RecipeWithFullData

    /**
     * Inserts a new shopping item into the local database.
     *
     * @param shoppingItem The [ShoppingItem] to be inserted.
     */
    @Insert
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    /**
     * Updates an existing shopping item in the local database.
     *
     * @param shoppingItem The [ShoppingItem] to be updated.
     */
    @Update
    suspend fun updateShoppingItem(shoppingItem: ShoppingItem)

    /**
     * Deletes a shopping item from the local database.
     *
     * @param shoppingItem The [ShoppingItem] to be deleted.
     */
    @Delete
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

}