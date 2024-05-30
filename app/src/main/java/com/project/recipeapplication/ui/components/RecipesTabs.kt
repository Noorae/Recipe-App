package com.project.recipeapplication.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
/**
 * Composable function for displaying tabs for switching between "My Recipes" and "Inspiration" sections.
 *
 * @param selectedTabIndex The index of the currently selected tab.
 * @param onTabSelected Callback function invoked when a tab is selected. It returns the index of the selected tab.
 */
@Composable
fun RecipesTabs(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {

    val tabs = listOf("My Recipes", "Inspiration")

    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier.fillMaxWidth()
    ) {
        tabs.forEachIndexed { index, tabName ->
            Tab(
                text = { Text(tabName) },
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) }
            )
        }
    }

}