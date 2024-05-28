package com.project.recipeapplication.data.model.database

import androidx.room.Embedded
import androidx.room.Relation

data class RecipeWithFullData(
    @Embedded val recipe: PersonalRecipe,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val instructions: List<InstructionStep>,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val ingredients: List<Ingredient>,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val tags: List<Tag>
)