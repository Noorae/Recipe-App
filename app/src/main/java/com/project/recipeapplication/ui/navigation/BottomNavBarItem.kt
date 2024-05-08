package com.project.recipeapplication.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavBarItem(
    val label : String = "",
    val selectedIcon: ImageVector,
    val unSelectedIcon : ImageVector,
    val route : String = ""
)