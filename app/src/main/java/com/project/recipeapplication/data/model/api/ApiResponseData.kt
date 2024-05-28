package com.project.recipeapplication.data.model.api

import com.project.recipeapplication.data.model.api.ApiRecipe

data class ApiResponseData(
    val results: List<ApiRecipe>
)

