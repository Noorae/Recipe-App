package com.project.recipeapplication.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing a favorite recipe obtained from an external API.
 *
 * @property id The unique identifier of the recipe in the local database.
 * @property apiId The unique identifier of the recipe obtained from the external API.
 * @property imagePath The URL of the recipe's image.
 * @property title The title or name of the recipe.
 */
@Entity(tableName = "api_recipes")
data class ApiFavoriteRecipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val apiId: Int,
    val imagePath: String,
    val title: String


)