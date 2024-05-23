package com.project.recipeapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.project.recipeapplication.R
import com.project.recipeapplication.data.model.PersonalRecipe
import com.project.recipeapplication.ui.components.RecipeList
import com.project.recipeapplication.ui.components.RecipesTabs
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel

@Composable
fun Recipes(navController: NavController, viewModel: PersonalRecipeViewModel = viewModel()) {
    val recipeState = viewModel.personalRecipes.collectAsState()
    val personalRecipes = recipeState.value

    var selectedTabIndex by remember { mutableIntStateOf(0) }


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier = Modifier
            .align(Alignment.Start)
            .padding(start = 16.dp, top = 20.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            text = "Recipes"
        )
        RecipesTabs(
            selectedTabIndex = selectedTabIndex,
            onTabSelected = { index -> selectedTabIndex = index }
        )
        Divider()

        Box(modifier = Modifier.fillMaxSize()) {
            when (selectedTabIndex) {
                0 -> RecipeList(recipes = personalRecipes)
                1 -> Text(text = "Apin favoritet")
            }
            
            ExtendedFloatingActionButton(
                text = { Text(text = "new recipe") },
                icon = { Icon(Icons.Filled.Create, "Extended floating action button") },
                onClick = { navController.navigate("addRecipe") },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd))

        }

        
    }
    

}