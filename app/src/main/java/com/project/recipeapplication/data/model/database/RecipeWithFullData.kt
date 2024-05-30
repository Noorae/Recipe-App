package com.project.recipeapplication.data.model.database

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Data class representing a recipe along with its associated instructions, ingredients, and tags.
 *
 * @property recipe The [PersonalRecipe] object representing the recipe itself.
 * @property instructions The list of [InstructionStep] objects representing the preparation steps for the recipe.
 * @property ingredients The list of [Ingredient] objects representing the ingredients used in the recipe.
 * @property tags The list of [Tag] objects representing the tags associated with the recipe.
 */
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