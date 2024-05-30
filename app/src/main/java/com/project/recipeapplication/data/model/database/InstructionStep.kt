package com.project.recipeapplication.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing an instruction step for preparing a recipe.
 *
 * @property id The unique identifier of the instruction step.
 * @property recipeId The ID of the recipe to which the instruction step belongs.
 * @property stepNumber The sequential number of the instruction step.
 * @property description The description of the instruction step.
 */
@Entity(tableName = "instruction_steps")
data class InstructionStep(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var recipeId: Int,
    val stepNumber: Int,
    val description: String
)