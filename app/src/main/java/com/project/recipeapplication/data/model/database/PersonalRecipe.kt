package com.project.recipeapplication.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing a personal recipe stored locally.
 *
 * @property id The unique identifier of the recipe.
 * @property title The title or name of the recipe.
 * @property imagePath The path to the image associated with the recipe.
 * @property readyInMinutes The time required to prepare the recipe.
 * @property mealType The type or category of the recipe.
 * @property servingSize The number of servings the recipe yields.
 * @property isFavorite Indicates whether the recipe is marked as a favorite.
 */
@Entity(tableName = "recipes")
data class PersonalRecipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val imagePath: String,
    val readyInMinutes: String,
    val mealType: String,
    val servingSize: Int,
    val isFavorite: Boolean

)