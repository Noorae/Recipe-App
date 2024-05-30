package com.project.recipeapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Divider
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.recipeapplication.data.model.database.ShoppingItem
import com.project.recipeapplication.ui.components.CustomTopBar
import com.project.recipeapplication.ui.components.ShoppingItemDialog
import com.project.recipeapplication.ui.components.ShoppingItems
import com.project.recipeapplication.viewModel.ShoppingViewModel

/**
 * Composable function representing the shopping list screen.
 *
 * @param navController The navigation controller to handle navigation.
 * @param viewModel The view model for managing shopping list data.
 */
@Composable
fun ShoppingList(navController: NavController, viewModel : ShoppingViewModel) {
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTopBar(
            title = "Shopping list" ,
            navController =  navController,
        )
        Divider()

        Box(modifier = Modifier.fillMaxSize()) {
            ShoppingItems(viewModel = viewModel)

            ExtendedFloatingActionButton(
                text = { Text(text = "new item") },
                icon = { Icon(Icons.Filled.Create, "Extended floating action button") },
                onClick = { showDialog = true },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd))

        }

    }

    if (showDialog) {
        ShoppingItemDialog(
            onDismiss = { showDialog = false },
            onAddItem = { newItemName ->
                viewModel.addNewItem(ShoppingItem(name = newItemName, itemChecked = false))
            }
        )
    }

}