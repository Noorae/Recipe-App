package com.project.recipeapplication.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * Composable function for displaying a custom top app bar with settings options.
 *
 * @param title The title to be displayed in the app bar.
 * @param navController The navigation controller for navigating between composables.
 * @param showBackButton Whether to show the back button in the app bar.
 * @param isDarkTheme Boolean representing whether the current theme is dark or light.
 * @param onToggleTheme Callback function for toggling between dark and light themes.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSettingsTopBar(
    title: String,
    navController: NavController,
    showBackButton: Boolean = false,
    isDarkTheme : Boolean = false,
    onToggleTheme: (Boolean) -> Unit = {}
) {
    val menuExpanded = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Arrow to navigate back"
                    )

                }
            } else {
                IconButton(onClick = { menuExpanded.value = !menuExpanded.value }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Hamburger menu"
                    )
                }
            }
        },
        actions = {
            DropdownMenu(
                expanded = menuExpanded.value,
                onDismissRequest = { menuExpanded.value = false },
            ) {
                DropdownMenuItem(text = { Text("Settings") }, onClick = { /*TODO*/ })
                Divider()
                DropdownMenuItem(
                    onClick = { onToggleTheme(!isDarkTheme)},
                    text = { if (isDarkTheme) Text(text = "Dark Theme") else Text(text = "Light Theme") },
                    trailingIcon = {
                        Icon(
                            imageVector = if (isDarkTheme) Icons.Filled.ToggleOn else Icons.Filled.ToggleOff,
                            contentDescription = if (isDarkTheme) "Dark Mode" else "Light Mode",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                )

            }
        }

    )

}