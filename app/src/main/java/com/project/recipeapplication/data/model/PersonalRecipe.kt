package com.project.recipeapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class PersonalRecipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val instructions: String,
    val imagePath: String
)