package com.project.recipeapplication.data.local

import android.content.Context
import androidx.room.Room

object DatabaseInstance {

    fun getDatabaseInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "recipe-database"
        ).build()
    }
}