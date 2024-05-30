package com.project.recipeapplication.data.repository

import com.project.recipeapplication.RecipeApplication
import com.project.recipeapplication.data.model.database.Ingredient
import com.project.recipeapplication.data.model.database.InstructionStep
import com.project.recipeapplication.data.model.database.PersonalRecipe
import com.project.recipeapplication.data.model.database.RecipeSummary
import com.project.recipeapplication.data.model.database.RecipeWithFullData
import com.project.recipeapplication.data.model.database.Tag

/**
 * Repository class for managing personal recipe data.
 */
class PersonalRecipeRepository() {

    /**
     * Fetches all personal recipes.
     *
     * @return A list of [RecipeSummary] objects representing the fetched recipes.
     */
    suspend fun fetchRecipes(): List<RecipeSummary> {
        val recipes = RecipeApplication.database.personalRecipeDao().getAll()
        println(recipes)
        return recipes
    }

    /**
     * Adds a new personal recipe along with its ingredients, instructions, and tags to the database.
     *
     * @param recipe The [PersonalRecipe] object representing the recipe to be added.
     * @param ingredients The list of [Ingredient] objects representing the ingredients of the recipe.
     * @param instructions The list of [InstructionStep] objects representing the instructions of the recipe.
     * @param tags The list of [Tag] objects representing the tags associated with the recipe.
     */
    suspend fun addRecipe(recipe : PersonalRecipe,
                          ingredients: List<Ingredient>,
                          instructions: List<InstructionStep>,
                          tags: List<Tag>
                          ) {
        val recipeId = RecipeApplication.database.personalRecipeDao().insertRecipe(recipe).toInt()
        println("Successfully added recipe with id $recipeId")

        if (ingredients.isNotEmpty()) {
            ingredients.forEach {it.recipeId = recipeId }
            println("Successfully added ingredients to recipe with id $recipeId")
            RecipeApplication.database.personalRecipeDao().insertIngredients(ingredients)
        } else {println("ingredients list was empty")}
        if (instructions.isNotEmpty()) {
            instructions.forEach {it.recipeId = recipeId}
            println("Successfully added instructions to recipe with id $recipeId")
            RecipeApplication.database.personalRecipeDao().insertInstructionSteps(instructions)

        } else {
            println("instructions list was empty")
        }

        if (tags.isNotEmpty()) {
            tags.forEach {it.recipeId = recipeId}
            println("Successfully added tags with id $recipeId")
            RecipeApplication.database.personalRecipeDao().insertTags(tags)

        } else {
            println("instructions list was empty")
        }

    }

    /**
     * Fetches the full data of a specific personal recipe.
     *
     * @param recipeId The ID of the recipe.
     * @return A [RecipeWithFullData] object representing the full data of the recipe.
     */
    suspend fun getRecipeWithFullData(recipeId: Int): RecipeWithFullData {
        return RecipeApplication.database.personalRecipeDao().getRecipeWithFullData(recipeId)
    }

    /**
     * Deletes a personal recipe by its ID along with its associated ingredients, instructions, and tags.
     *
     * @param id The ID of the recipe to be deleted.
     */
    suspend fun deleteRecipeById(id : Int) {
        RecipeApplication.database.personalRecipeDao().deleteRecipeById(id)
        RecipeApplication.database.personalRecipeDao().deleteIngredientsByRecipeId(id)
        RecipeApplication.database.personalRecipeDao().deleteInstructionStepsByRecipeId(id)
        RecipeApplication.database.personalRecipeDao().deleteTagsByRecipeId(id)
    }

    /**
     * Fetches a random personal recipe with full data.
     *
     * @return A [RecipeWithFullData] object representing the full data of the random recipe.
     */
    suspend fun getRandomRecipe() : RecipeWithFullData {
        return RecipeApplication.database.personalRecipeDao().getRandomRecipe()
    }

    /**
     * Fetches the newest personal recipe with full data.
     *
     * @return A [RecipeWithFullData] object representing the full data of the newest recipe.
     */
    suspend fun getNewestRecipe() : RecipeWithFullData {
        return RecipeApplication.database.personalRecipeDao().getNewestRecipe()
    }

}