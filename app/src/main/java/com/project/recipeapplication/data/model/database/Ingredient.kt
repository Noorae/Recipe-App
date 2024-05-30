package com.project.recipeapplication.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing an ingredient used in a recipe.
 *
 * @property id The unique identifier of the ingredient.
 * @property recipeId The ID of the recipe to which the ingredient belongs.
 * @property name The name of the ingredient.
 * @property amount The quantity or amount of the ingredient.
 * @property unit The unit of measurement for the ingredient quantity.
 */
@Entity(tableName = "ingredients")
data class Ingredient(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var recipeId: Int,
    val name: String,
    val amount: Double,
    val unit: String
)