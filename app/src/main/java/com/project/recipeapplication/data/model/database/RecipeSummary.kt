package com.project.recipeapplication.data.model.database

/**
 * Data class representing a summary of a recipe.
 *
 * @property id The unique identifier of the recipe.
 * @property title The title or name of the recipe.
 * @property imagePath The path to the image associated with the recipe.
 * @property mealType The type or category of the recipe.
 * @property isFavorite Indicates whether the recipe is marked as a favorite.
 */
data class RecipeSummary(
    val id: Int,
    val title: String,
    val imagePath: String,
    val mealType: String,
    val isFavorite: Boolean
)