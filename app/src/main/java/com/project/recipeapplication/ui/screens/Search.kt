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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.project.recipeapplication.R
import com.project.recipeapplication.viewModel.RecipesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(navController: NavController, viewModel: RecipesViewModel = viewModel()) {
    val searchQuery = viewModel.searchQuery
    val recipes = viewModel.recipes
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Search recipes", modifier = Modifier
            .align(Alignment.Start)
            .padding(start = 16.dp, top = 15.dp), fontSize = 20.sp)
        SearchBar(modifier = Modifier.padding(8.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            },
            query = searchQuery ,
            onQueryChange = { newQuery ->
                viewModel.updateSearchQuery(newQuery)
                if (newQuery.length >= 3) {
                    coroutineScope.launch {
                        delay(2000)
                        viewModel.fetchRecipes()
                    }
                } },
            onSearch = { if (searchQuery.length >= 3) { viewModel.fetchRecipes()} },
            active = false,
            onActiveChange = {/*TODO*/}) {

        }
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