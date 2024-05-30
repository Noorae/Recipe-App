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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.recipeapplication.viewModel.ApiRecipesViewModel
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel

@Composable
fun PersonalRecipeInfo(navController: NavController, viewModel: PersonalRecipeViewModel) {

    val recipe = viewModel.selectedRecipeDetails.collectAsState()
    val selectedRecipe = recipe.value


    SideEffect {
        println("Current Recipe Details: $selectedRecipe")
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

            Text(text = selectedRecipe.recipe.title)

            Divider()
            //TODO row serving modificator
            Text(text = "Serving size {${selectedRecipe.recipe.servingSize}}")

            LazyColumn {
                items(selectedRecipe.ingredients) { ingredient ->
                    Row() {
                        Text(text = ingredient.amount.toString() ?: "No amount")
                        Text(text = ingredient.unit ?: "No unit")
                        Text(text = ingredient.name ?: "No ingredient name")
                    }
                }
            }
            Divider()
            LazyColumn {
                items(selectedRecipe.instructions) { instruction ->
                    Row(modifier = Modifier.padding(8.dp)) {
                        Text(text = instruction.stepNumber.toString())
                        Text(text = instruction.description)
                    }
                }
            }



        } else {
            CircularProgressIndicator()
        }

    }

}