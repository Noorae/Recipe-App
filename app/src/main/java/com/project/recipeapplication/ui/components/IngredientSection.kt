package com.project.recipeapplication.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel

/**
 * Composable function for displaying the ingredients section of a recipe.
 *
 * @param viewModel The view model containing the logic for managing personal recipes.
 */
@Composable
fun IngredientsSection(viewModel: PersonalRecipeViewModel) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(
            text = "Recipe Ingredients",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        viewModel.recipeIngredients.forEachIndexed { index, ingredient ->
            Row {
                OutlinedTextField(
                    value = if (ingredient.amount == 0.0) "" else ingredient.amount.toString(),
                    onValueChange = {
                        viewModel.recipeIngredients[index] = ingredient.copy(amount = it.toDoubleOrNull() ?: 0.0)
                    },
                    label = { Text("Amount", style = TextStyle(fontSize = 14.sp)) },
                    modifier = Modifier.weight(1f).padding(2.dp)
                )
                OutlinedTextField(
                    value = ingredient.unit,
                    onValueChange = {
                        viewModel.recipeIngredients[index] =
                            ingredient.copy(unit = it)
                    },
                    label = { Text("Unit", style = TextStyle(fontSize = 14.sp)) },
                    modifier = Modifier.weight(1f).padding(2.dp)
                )
                OutlinedTextField(
                    value = ingredient.name,
                    onValueChange = {
                        viewModel.recipeIngredients[index] =
                            ingredient.copy(name = it)
                    },
                    label = { Text("Ingredient", style = TextStyle(fontSize = 14.sp)) },
                    modifier = Modifier.weight(2f).padding(2.dp)
                )
            }
        }

        Button(onClick = { viewModel.addIngredient() }) {
            Text(text = "+")
        }
    }
}