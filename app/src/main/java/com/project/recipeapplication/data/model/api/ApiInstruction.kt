package com.project.recipeapplication.data.model.api

/**
 * Data class representing an instruction step for preparing a recipe obtained from an external API.
 *
 * @property number The sequential number of the instruction step.
 * @property step The description of the instruction step.
 */
data class ApiInstruction(
    val number: Int,
    val step: String
)