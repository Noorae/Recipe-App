package com.project.recipeapplication.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class Tag(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var recipeId: Int,
    val tagName: String,
)