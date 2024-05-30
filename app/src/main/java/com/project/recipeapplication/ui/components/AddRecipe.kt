package com.project.recipeapplication.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel


@Composable
fun AddRecipe(navController: NavController, viewModel: PersonalRecipeViewModel) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
        .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTopBar(
            title = "Add new recipe" ,
            navController =  navController,
            showBackButton = true,

        )
        Divider()
        Column(modifier= Modifier.padding(vertical = 10.dp)) {
            
            RecipeSection(viewModel = viewModel)
            Divider()
            IngredientsSection(viewModel = viewModel)
            Divider()
            InstructionsSection(viewModel = viewModel)
            Divider()
            TagSection(viewModel = viewModel)


        }

        Button(onClick = {
            viewModel.collectRecipeData()
            navController.navigate("recipes")
        }) {
            Text("Save")
        }
        
    }

}