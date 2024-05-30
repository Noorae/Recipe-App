package com.project.recipeapplication.data.repository

import com.project.recipeapplication.RecipeApplication
import com.project.recipeapplication.data.model.database.ShoppingItem

class ShoppingListRepository() {

    //fetch all shoppinglist items
    suspend fun fetchShoppingList(): List<ShoppingItem> {
        val shoppingItems = RecipeApplication.database.personalRecipeDao().getShoppingList()
        println(shoppingItems)
        return shoppingItems
    }

    // add new sopping item
    suspend fun addShoppingItem(item: ShoppingItem) {
        RecipeApplication.database.personalRecipeDao().insertShoppingItem(item)

    }

    //update shopping item
    suspend fun updateShoppingItem(item: ShoppingItem) {
        RecipeApplication.database.personalRecipeDao().updateShoppingItem(item)
    }

    //delete shopping item
    suspend fun removeShoppingItem(item: ShoppingItem) {
        RecipeApplication.database.personalRecipeDao().deleteShoppingItem(item)
    }

}

