package com.project.recipeapplication.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class PersonalRecipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val imagePath: String,
    val readyInMinutes: String,
    val mealType: String,
    val servingSize: Int,
    val isFavorite: Boolean

)