package com.project.recipeapplication.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.project.recipeapplication.viewModel.ApiRecipesViewModel

/**
 * Composable function for displaying a list of favorite recipes fetched from an API.
 *
 * @param navController The navigation controller for navigating between composables.
 * @param modifier The modifier for the list.
 * @param viewModel The view model containing the logic for managing API recipes.
 */
@Composable
fun ApiFavoriteRecipeList(navController: NavController, modifier: Modifier = Modifier, viewModel: ApiRecipesViewModel) {
    val recipes = viewModel.apiFavoriteRecipes


    LazyColumn(modifier = Modifier
        .padding(5.dp)) {
        items(recipes) { recipe ->
            ListItem(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(
                        onClick = {
                            viewModel.selectedId = recipe.apiId
                            viewModel.fetchRecipeDetails(recipe.apiId)
                            navController.navigate("apiRecipeInfo")
                        },
                    ),
                colors = ListItemDefaults.colors(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)),
                headlineContent = { Text(recipe.title) },
                leadingContent = {
                    AsyncImage(
                        model = recipe.imagePath,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clip(RoundedCornerShape(10.dp))
                    )
                },
                trailingContent = {
                    IconButton(
                        onClick = {
                            println("deletebutton with recipe id ${recipe.id} clicked")
                            viewModel.deleteFromFavorites(recipe)

                        }

                    ) { Icon(imageVector = Icons.Filled.Delete, contentDescription = "Icon to display more options") }
                })

        }
    }
}