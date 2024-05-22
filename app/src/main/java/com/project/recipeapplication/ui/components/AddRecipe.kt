package com.project.recipeapplication.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel

// TODO composable to add personal recipes into the database
@Composable
fun AddRecipe(navController: NavController, viewModel: PersonalRecipeViewModel = viewModel()) {
    Column {
        Text(text = "this is the AddRecipe Composable to be implemented")
        TextField(
            value = viewModel.recipeTitle,
            onValueChange = { viewModel.recipeTitle = it},
            label = { Text("Recipe name")},
            )
        TextField(
            value = viewModel.recipeInstructions,
            onValueChange = {viewModel.recipeInstructions = it},
            label = { Text("Recipe instructions")},
            )
        TextButton(onClick = {
            viewModel.collectRecipeData()
            navController.navigate("recipes")
        }) {
            Text("Save")
        }
        
    }
    

}