package com.project.recipeapplication.data.repository

import com.project.recipeapplication.MainActivity
import com.project.recipeapplication.RecipeApplication
import com.project.recipeapplication.data.model.database.Ingredient
import com.project.recipeapplication.data.model.database.InstructionStep
import com.project.recipeapplication.data.model.database.PersonalRecipe
import com.project.recipeapplication.data.model.database.RecipeSummary
import com.project.recipeapplication.data.model.database.RecipeWithFullData
import com.project.recipeapplication.data.model.database.Tag

class PersonalRecipeRepository() {

    suspend fun fetchRecipes(): List<RecipeSummary> {
        val recipes = RecipeApplication.database.personalRecipeDao().getAll()
        println(recipes)
        return recipes
    }

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

    suspend fun getRecipeWithFullData(recipeId: Int): RecipeWithFullData {
        return RecipeApplication.database.personalRecipeDao().getRecipeWithFullData(recipeId)
    }

    suspend fun deleteRecipeById(id : Int) {
        RecipeApplication.database.personalRecipeDao().deleteRecipeById(id)
        RecipeApplication.database.personalRecipeDao().deleteIngredientsByRecipeId(id)
        RecipeApplication.database.personalRecipeDao().deleteInstructionStepsByRecipeId(id)
        RecipeApplication.database.personalRecipeDao().deleteTagsByRecipeId(id)
    }

    suspend fun getRandomRecipe() : RecipeWithFullData {
        return RecipeApplication.database.personalRecipeDao().getRandomRecipe()
    }

    suspend fun getNewestRecipe() : RecipeWithFullData {
        return RecipeApplication.database.personalRecipeDao().getNewestRecipe()
    }

}