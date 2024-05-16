package com.project.recipeapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.project.recipeapplication.ui.components.RecipeSearchBar
import com.project.recipeapplication.viewModel.ApiRecipesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(navController: NavController, viewModel: ApiRecipesViewModel = viewModel()) {
    val searchQuery = viewModel.searchQuery
    val recipes = viewModel.recipes


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(modifier = Modifier
            .align(Alignment.Start)
            .padding(start = 16.dp, top = 20.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            text = "Search recipes"
        )
        RecipeSearchBar(
            searchQuery = searchQuery,
            onQueryChange = { newQuery -> viewModel.updateSearchQuery(newQuery) },
            onSearch = { viewModel.fetchRecipes() }
        )
        LazyColumn {
            items(recipes) { recipe ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant,),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp))
                {
                    Row(modifier = Modifier.padding(8.dp)) {
                        AsyncImage(
                            model = recipe.image,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(RoundedCornerShape(15.dp))
                        )
                        Text(text = recipe.title,
                            modifier = Modifier.padding(10.dp))
                        IconButton(onClick = { /*TODO*/ }) {
                            
                        }
                    }

                }

            }
        }

    }

}