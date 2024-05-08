package com.example.recipeapplication.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavBarItem(
    val label : String = "",
    val selectedIcon: ImageVector,
    val unSelectedIcon : ImageVector,
    val route : String = ""
)