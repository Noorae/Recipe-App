package com.project.recipeapplication.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel
/**
 * Composable function for displaying a section for adding or editing recipe details.
 *
 * @param viewModel The view model for handling recipe data.
 */
@Composable
fun RecipeSection(viewModel: PersonalRecipeViewModel) {



    Column {
        Row {
            Box(modifier = Modifier
                .weight(1f)
                .padding(8.dp)
                .aspectRatio(1f),
                contentAlignment = Alignment.Center

            ) {
            ImageSection(viewModel)
        }


            Box(modifier = Modifier
                .weight(1f)
                .padding(8.dp)) {
                Column {
                    OutlinedTextField(
                        value = viewModel.recipeTitle,
                        onValueChange = { viewModel.recipeTitle = it},
                        label = { Text("Recipe name") },
                        modifier = Modifier.padding(5.dp)
                    )

                    OutlinedTextField(
                        value = if (viewModel.recipeServingSize == 0){
                            ""} else { viewModel.recipeServingSize.toString()}
                        ,
                        onValueChange = { viewModel.recipeServingSize = it.toIntOrNull() ?: 0 },
                        label = { Text("Servings") },
                        modifier = Modifier.padding(5.dp)
                    )

                }

            }
        }
        Divider()
        //  TODO ADD MISSING VALUES
        OutlinedTextField(
            value = viewModel.recipeMealType,
            onValueChange = { viewModel.recipeMealType = it},
            label = { Text("Meal Type") },
            modifier = Modifier.padding(5.dp)
        )
        OutlinedTextField(
            value = viewModel.recipeReady,
            onValueChange = { viewModel.recipeReady = it},
            label = { Text("Cooking time") },
            modifier = Modifier.padding(6.dp)
        )

    }

}

