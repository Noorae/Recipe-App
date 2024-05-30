package com.project.recipeapplication.data.model.api

/**
 * Data class representing an ingredient used in a recipe obtained from an external API.
 *
 * @property name The name of the ingredient.
 * @property amount The quantity or amount of the ingredient.
 * @property unit The unit of measurement for the ingredient quantity.
 */
data class ApiIngredient(
    val name : String,
    val amount : Double,
    val unit : String
    )