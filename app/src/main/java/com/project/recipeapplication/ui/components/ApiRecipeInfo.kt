package com.project.recipeapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.project.recipeapplication.data.model.ApiDetailedRecipe
import com.project.recipeapplication.viewModel.ApiRecipesViewModel

@Composable
fun ApiRecipeInfo(navController: NavController, viewModel: ApiRecipesViewModel) {

    val recipe = viewModel.selectedRecipeDetails.collectAsState()
    val selectedRecipe = recipe.value

    SideEffect {
        println("Current Recipe Details: $recipe")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTopBar(
            title = "Recipe details" ,
            navController =  navController,
            showBackButton = true
        )

        if (selectedRecipe != null) {
            Text(text = selectedRecipe.title ?: "No title")
            Text(text= selectedRecipe.credits ?: "No credits")
            Divider()
            //TODO row serving modificator
            Text(text = "Serving size {${selectedRecipe.servings ?: "No servings size"}}")
            LazyColumn {
                items(selectedRecipe.extendedIngredients) { ingredient ->
                    Row() {
                        Text(text = ingredient.amount.toString() ?: "No amount")
                        Text(text = ingredient.unit ?: "No unit")
                        Text(text = ingredient.name ?: "No ingredient name")
                    }
                }
            }
            Divider()
            LazyColumn {
                items(selectedRecipe.analyzedInstructions[0].steps) { instruction ->
                    Row(modifier = Modifier.padding(8.dp)) {
                        Text(text = instruction.number.toString())
                        Text(text = instruction.step)
                    }
                }
            }

        } else {
            CircularProgressIndicator()
        }

    }

}