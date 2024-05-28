package com.project.recipeapplication.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "instruction_steps")
data class InstructionStep(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var recipeId: Int,
    val stepNumber: Int,
    val description: String
)