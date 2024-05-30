package com.project.recipeapplication.data.model.api

/**
 * Data class representing the response data obtained from an external API.
 *
 * @property results The list of [ApiRecipe] objects containing recipe information.
 */
data class ApiResponseData(
    val results: List<ApiRecipe>
)

