package com.project.recipeapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.recipeapplication.data.model.database.ApiFavoriteRecipe
import com.project.recipeapplication.data.model.database.Ingredient
import com.project.recipeapplication.data.model.database.InstructionStep
import com.project.recipeapplication.data.model.database.PersonalRecipe
import com.project.recipeapplication.data.model.database.ShoppingItem
import com.project.recipeapplication.data.model.database.Tag

/**
 * Room database class for managing local data storage.
 */
@Database(entities = [PersonalRecipe::class, Ingredient::class, InstructionStep::class, Tag::class, ShoppingItem::class, ApiFavoriteRecipe::class], version = 4)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Gets the Data Access Object (DAO) for managing personal recipe data.
     *
     * @return The [PersonalRecipeDao] instance.
     */
    abstract fun personalRecipeDao(): PersonalRecipeDao

    /**
     * Gets the Data Access Object (DAO) for managing API recipe data.
     *
     * @return The [ApiRecipeDao] instance.
     */
    abstract fun apiRecipeDao(): ApiRecipeDao
}