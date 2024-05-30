package com.project.recipeapplication

import android.content.Context


class ThemePreference(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "theme_prefs"
        private const val KEY_IS_DARK_THEME = "is_dark_theme"
    }

    fun setDarkTheme(isDark: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_IS_DARK_THEME, isDark).apply()
    }

    fun isDarkTheme(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_DARK_THEME, false)
    }
}