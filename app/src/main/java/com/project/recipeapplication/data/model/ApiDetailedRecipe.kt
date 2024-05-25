package com.project.recipeapplication.data.model

data class ApiDetailedRecipe(
    val id : Int,
    val title : String,
    val image : String,
    val servings : Int,
    val readyInMinutes : Int,
    val credits: String,
    val dishType: List<String>,
    val ingredients: List<ApiIngredient>,

    //TODO ADD val instructions : List<String>,

    )