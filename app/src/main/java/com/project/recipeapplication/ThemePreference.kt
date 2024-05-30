package com.project.recipeapplication

import android.content.Context


/**
 * Class to manage theme preferences using [SharedPreferences].
 *
 * @param context The application context used to access the SharedPreferences.
 */
class ThemePreference(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "theme_prefs"
        private const val KEY_IS_DARK_THEME = "is_dark_theme"
    }

    /**
     * Sets the theme preference to either dark mode or light mode.
     *
     * @param isDark Boolean indicating if dark mode should be enabled.
     */
    fun setDarkTheme(isDark: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_IS_DARK_THEME, isDark).apply()
    }

    /**
     * Checks the current theme preference.
     *
     * @return Boolean indicating if dark mode is enabled.
     */
    fun isDarkTheme(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_DARK_THEME, false)
    }
}