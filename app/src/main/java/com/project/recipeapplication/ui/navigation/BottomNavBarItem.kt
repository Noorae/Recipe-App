package com.project.recipeapplication.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data class representing an item in the bottom navigation bar.
 *
 * @property label The label or text associated with the item.
 * @property selectedIcon The icon to be displayed when the item is selected.
 * @property unSelectedIcon The icon to be displayed when the item is not selected.
 * @property route The route or destination associated with the item.
 */
data class BottomNavBarItem(
    val label : String = "",
    val selectedIcon: ImageVector,
    val unSelectedIcon : ImageVector,
    val route : String = ""
)