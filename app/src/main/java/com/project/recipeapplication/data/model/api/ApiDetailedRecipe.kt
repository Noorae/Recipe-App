package com.project.recipeapplication.data.model.api

/**
 * Data class representing detailed information about a recipe obtained from an external API.
 *
 * @property id The unique identifier of the recipe.
 * @property title The title or name of the recipe.
 * @property image The URL of the recipe's image.
 * @property servings The number of servings the recipe yields.
 * @property readyInMinutes The time required to prepare the recipe (in minutes).
 * @property credits The attribution or credits for the recipe.
 * @property dishType The type of dish the recipe belongs to (e.g., main course, dessert).
 * @property extendedIngredients The list of detailed ingredients used in the recipe.
 * @property analyzedInstructions The list of analyzed instructions for preparing the recipe.
 */
data class ApiDetailedRecipe(
    val id : Int,
    val title : String,
    val image : String,
    val servings : Int,
    val readyInMinutes : Int,
    val credits: String,
    val dishType: List<String>,
    val extendedIngredients: List<ApiIngredient>,
    val analyzedInstructions : List<ParsedInstructions>,

    )