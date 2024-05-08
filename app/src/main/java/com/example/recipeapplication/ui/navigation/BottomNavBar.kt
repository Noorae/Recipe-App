package com.example.recipeapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapplication.ui.screens.DashBoard

@Composable
fun BottomNavBar() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "dashboard") {
        composable("dashboard") { DashBoard()}
    }
}