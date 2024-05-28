package com.project.recipeapplication.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class Ingredient(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var recipeId: Int,
    val name: String,
    val amount: Double,
    val unit: String
)