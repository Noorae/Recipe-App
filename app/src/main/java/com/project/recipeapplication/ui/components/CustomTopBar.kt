package com.project.recipeapplication.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

/**
 * Composable function for displaying a custom top app bar.
 *
 * @param title The title to be displayed in the app bar.
 * @param navController The navigation controller for navigating between composables.
 * @param showBackButton Whether to show the back button in the app bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: String,
    navController: NavController,
    showBackButton: Boolean = false,
) {

    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Arrow to navigate back"
                    )

                }
            }
        },


    )

}