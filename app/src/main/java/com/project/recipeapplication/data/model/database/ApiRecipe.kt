package com.project.recipeapplication.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "api_recipes")
data class ApiFavoriteRecipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val apiId: Int,
    val imagePath: String,
    val title: String


)