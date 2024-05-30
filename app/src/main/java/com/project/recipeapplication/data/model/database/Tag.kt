package com.project.recipeapplication.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing a tag associated with a recipe.
 *
 * @property id The unique identifier of the tag.
 * @property recipeId The ID of the recipe to which the tag belongs.
 * @property tagName The name or label of the tag.
 */
@Entity(tableName = "tags")
data class Tag(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var recipeId: Int,
    val tagName: String,
)