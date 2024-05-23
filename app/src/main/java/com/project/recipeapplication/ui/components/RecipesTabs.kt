package com.project.recipeapplication.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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