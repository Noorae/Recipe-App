package com.project.recipeapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.project.recipeapplication.data.model.database.ApiFavoriteRecipe
import com.project.recipeapplication.ui.components.CustomTopBar
import com.project.recipeapplication.ui.components.RecipeSearchBar
import com.project.recipeapplication.ui.theme.RecipeApplicationTheme
import com.project.recipeapplication.viewModel.ApiRecipesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(navController: NavController, viewModel: ApiRecipesViewModel) {
    val searchQuery = viewModel.searchQuery
    val recipes = viewModel.recipes
    val favoriteRecipes = viewModel.apiFavoriteRecipes

    LaunchedEffect(favoriteRecipes) {
        println("FavoriteRecipes updated")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CustomTopBar(
            title = "Search inspiration" ,
            navController =  navController,
        )
        Divider()
        RecipeSearchBar(
            searchQuery = searchQuery,
            onQueryChange = { newQuery -> viewModel.updateSearchQuery(newQuery) },
            onSearch = { viewModel.fetchRecipes() }
        )
        LazyColumn(modifier = Modifier
            .weight(1f)
            .padding(5.dp)) {
            items(recipes) { recipe ->
                val isFavorite = favoriteRecipes.any { it.apiId == recipe.id } ?: false
                ListItem(
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                viewModel.selectedId = recipe.id
                                viewModel.fetchRecipeDetails(recipe.id)
                                navController.navigate("apiRecipeInfo")
                            },
                        )
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    colors = ListItemDefaults.colors(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)),
                    headlineContent = { Text(recipe.title) },
                    leadingContent = {
                        AsyncImage(
                            model = recipe.image,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(RoundedCornerShape(10.dp))
                        )
                    }, trailingContent = {IconButton(
                        onClick = {
                            if (isFavorite) {
                                val favoriteRecipe = favoriteRecipes.find { it.apiId == recipe.id }
                                favoriteRecipe?.let {
                                    viewModel.deleteFromFavorites(it)
                                }
                            } else {
                                println("add to favorites with recipe id ${recipe.id} clicked")
                                viewModel.addToFavorites(
                                    ApiFavoriteRecipe(
                                        apiId = recipe.id,
                                        title = recipe.title,
                                        imagePath = recipe.image ?: ""
                                    )
                                )
                            }

                        }

                    ) { Icon(imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Icon to display favorite") }}

                )
            }
        }
    }
}