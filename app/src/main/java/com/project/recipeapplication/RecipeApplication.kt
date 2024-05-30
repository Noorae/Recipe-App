package com.project.recipeapplication

import android.app.Application
import androidx.room.Room
import com.project.recipeapplication.data.local.AppDatabase

/**
 * Custom [Application] class for the Recipe Application.
 * Initializes the Room database instance used across the application.
 */
class RecipeApplication : Application() {

    companion object {
        /**
         * The [AppDatabase] instance used by the application.
         * This instance is lazily initialized in the [onCreate] method.
         */
        lateinit var database: AppDatabase
            private set
    }

    /**
     * Called when the application is starting, before any activity, service, or receiver objects (excluding content providers) have been created.
     * This is where the Room database is initialized.
     */
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "recipe-database"
        ).build()
    }
}
