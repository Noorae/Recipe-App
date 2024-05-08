package com.example.recipeapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun Recipes(navController: NavController) {

    val mockRecipeList = listOf("Pasta", "Ice Cream", "Coffee", )

    LazyColumn {
        items(mockRecipeList) { recipe ->
            TextButton(
                onClick = { /*TODO*/ },
                ) { Text(text = recipe.toString())}

        }
    }

}