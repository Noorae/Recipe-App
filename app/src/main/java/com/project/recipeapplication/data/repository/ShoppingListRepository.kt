package com.project.recipeapplication.data.repository

import com.project.recipeapplication.RecipeApplication
import com.project.recipeapplication.data.model.database.ShoppingItem
/**
 * Repository class for managing the shopping list data.
 */
class ShoppingListRepository() {

    /**
     * Fetches all shopping list items.
     *
     * @return A list of [ShoppingItem] objects representing the fetched shopping list items.
     */
    suspend fun fetchShoppingList(): List<ShoppingItem> {
        val shoppingItems = RecipeApplication.database.personalRecipeDao().getShoppingList()
        println(shoppingItems)
        return shoppingItems
    }

    /**
     * Adds a new shopping item to the shopping list.
     *
     * @param item The [ShoppingItem] object representing the item to be added.
     */
    suspend fun addShoppingItem(item: ShoppingItem) {
        RecipeApplication.database.personalRecipeDao().insertShoppingItem(item)

    }

    /**
     * Updates an existing shopping item in the shopping list.
     *
     * @param item The [ShoppingItem] object representing the item to be updated.
     */
    suspend fun updateShoppingItem(item: ShoppingItem) {
        RecipeApplication.database.personalRecipeDao().updateShoppingItem(item)
    }

    /**
     * Removes a shopping item from the shopping list.
     *
     * @param item The [ShoppingItem] object representing the item to be removed.
     */
    suspend fun removeShoppingItem(item: ShoppingItem) {
        RecipeApplication.database.personalRecipeDao().deleteShoppingItem(item)
    }

}

