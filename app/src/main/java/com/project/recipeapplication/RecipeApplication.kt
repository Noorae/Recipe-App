package com.project.recipeapplication

import android.app.Application
import androidx.room.Room
import com.project.recipeapplication.data.local.AppDatabase

class RecipeApplication : Application() {

    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "recipe-database"
        ).build()
    }
}
