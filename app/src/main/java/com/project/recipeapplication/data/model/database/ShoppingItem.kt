package com.project.recipeapplication.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing an item in the shopping list.
 *
 * @property id The unique identifier of the shopping item.
 * @property name The name or description of the shopping item.
 * @property itemChecked Indicates whether the shopping item has been checked or purchased.
 */
@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val itemChecked : Boolean
)