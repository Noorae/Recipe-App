package com.project.recipeapplication.data.model.api


/**
 * Data class representing parsed instructions for preparing a recipe.
 *
 * @property steps The list of [ApiInstruction] objects containing individual steps of the instructions.
 */
data class ParsedInstructions(
    val steps: List<ApiInstruction>
)