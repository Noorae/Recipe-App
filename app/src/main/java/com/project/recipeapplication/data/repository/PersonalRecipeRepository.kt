package com.project.recipeapplication.data.repository

import com.project.recipeapplication.MainActivity
import com.project.recipeapplication.data.local.PersonalRecipeDao
import com.project.recipeapplication.data.model.PersonalRecipe

class PersonalRecipeRepository() {

    suspend fun fetchRecipes(): List<PersonalRecipe> {
        val recipes = MainActivity.database.personalRecipeDao().getAll()
        println(recipes)
        return recipes
    }

    suspend fun addRecipe(recipe : PersonalRecipe) {
        MainActivity.database.personalRecipeDao().upsertRecipe(recipe)
    }
}