package com.project.recipeapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Divider
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.recipeapplication.ui.components.ApiFavoriteRecipeList
import com.project.recipeapplication.ui.components.CustomTopBar
import com.project.recipeapplication.ui.components.RecipeList
import com.project.recipeapplication.ui.components.RecipesTabs
import com.project.recipeapplication.viewModel.ApiRecipesViewModel
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel

/**
 * Composable function representing the recipes screen.
 *
 * @param navController The navigation controller to handle navigation.
 * @param viewModel The view model for managing personal recipe data.
 * @param apiViewModel The view model for managing API recipe data.
 * @param selectedTabIndex The index of the selected tab.
 */
@Composable
fun Recipes(navController: NavController,
            viewModel: PersonalRecipeViewModel,
            apiViewModel : ApiRecipesViewModel,
            selectedTabIndex: Int) {
    var tabIndex by rememberSaveable { mutableIntStateOf(selectedTabIndex) }


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTopBar(
            title = "Recipes" ,
            navController =  navController,
        )
        RecipesTabs(
            selectedTabIndex = tabIndex,
            onTabSelected = { index -> tabIndex = index }
        )
        Divider()

        Box(modifier = Modifier.fillMaxSize()) {
            when (tabIndex) {
                0 ->  {
                    RecipeList(navController, viewModel = viewModel)
                    ExtendedFloatingActionButton(
                        text = { Text(text = "new recipe") },
                        icon = { Icon(Icons.Filled.Create, "Extended floating action button") },
                        onClick = { navController.navigate("addRecipe") },
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.BottomEnd)
                    )
                }
                1 -> ApiFavoriteRecipeList(navController, viewModel = apiViewModel)
            }
            


        }

        
    }
    

}