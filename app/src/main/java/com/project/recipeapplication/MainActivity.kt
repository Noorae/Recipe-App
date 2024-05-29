package com.project.recipeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.project.recipeapplication.data.local.AppDatabase
import com.project.recipeapplication.ui.navigation.BottomNavBar
import com.project.recipeapplication.ui.theme.RecipeApplicationTheme
import com.project.recipeapplication.viewModel.ApiRecipesViewModel
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel
import com.project.recipeapplication.viewModel.ShoppingViewModel

class MainActivity : ComponentActivity() {

    private val apiViewModel : ApiRecipesViewModel by viewModels()
    private val personalRecipeViewModel: PersonalRecipeViewModel by viewModels()
    private val shoppingViewModel: ShoppingViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomNavBar(apiViewModel, personalRecipeViewModel, shoppingViewModel)
                }
            }
        }
    }
}