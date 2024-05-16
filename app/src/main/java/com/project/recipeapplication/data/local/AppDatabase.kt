package com.project.recipeapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.recipeapplication.data.model.PersonalRecipe

@Database(entities = [PersonalRecipe::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personalRecipeDao(): PersonalRecipeDao
}