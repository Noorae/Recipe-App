package com.project.recipeapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.recipeapplication.data.model.database.Ingredient
import com.project.recipeapplication.data.model.database.InstructionStep
import com.project.recipeapplication.data.model.database.PersonalRecipe
import com.project.recipeapplication.data.model.database.ShoppingItem
import com.project.recipeapplication.data.model.database.Tag

@Database(entities = [PersonalRecipe::class, Ingredient::class, InstructionStep::class, Tag::class, ShoppingItem::class], version = 3)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personalRecipeDao(): PersonalRecipeDao
}