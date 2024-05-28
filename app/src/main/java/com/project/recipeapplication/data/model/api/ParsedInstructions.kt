package com.project.recipeapplication.data.model.api

import com.project.recipeapplication.data.model.api.ApiInstruction

data class ParsedInstructions(
    val steps: List<ApiInstruction>
)