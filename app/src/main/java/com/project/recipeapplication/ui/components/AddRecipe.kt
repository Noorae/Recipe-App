package com.project.recipeapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel

// TODO composable to add personal recipes into the database
@Composable
fun AddRecipe(navController: NavController, viewModel: PersonalRecipeViewModel = viewModel()) {

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTopBar(
            title = "Add new recipe" ,
            navController =  navController,
            showBackButton = true
        )
        Divider()
        Column(modifier= Modifier.padding(vertical = 10.dp)) {
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

        }

        Button(onClick = {
            viewModel.collectRecipeData()
            navController.navigate("recipes")
        }) {
            Text("Save")
        }
        
    }

}