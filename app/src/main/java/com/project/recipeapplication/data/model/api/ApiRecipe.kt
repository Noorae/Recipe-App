package com.project.recipeapplication.data.model.api

/**
 * Data class representing basic information about a recipe obtained from an external API.
 *
 * @property id The unique identifier of the recipe.
 * @property title The title or name of the recipe.
 * @property image The URL of the recipe's image, nullable if image is not available.
 */
data class ApiRecipe(
    val id: Int,
    val title: String,
    val image: String?,
    )