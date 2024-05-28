package com.project.recipeapplication.data.model.database

data class RecipeSummary(
    val id: Int,
    val title: String,
    val imagePath: String,
    val mealType: String,
    val isFavorite: Boolean
)