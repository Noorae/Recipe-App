package com.project.recipeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.project.recipeapplication.ui.navigation.BottomNavBar
import com.project.recipeapplication.ui.theme.RecipeApplicationTheme
import com.project.recipeapplication.viewModel.ApiRecipesViewModel
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel
import com.project.recipeapplication.viewModel.ShoppingViewModel

/**
 * Main activity for the Recipe Application.
 * This activity sets up the main content view and handles theme preferences.
 */
class MainActivity : ComponentActivity() {
    private lateinit var themePreferences: ThemePreference

    private val apiViewModel : ApiRecipesViewModel by viewModels()
    private val personalRecipeViewModel: PersonalRecipeViewModel by viewModels()
    private val shoppingViewModel: ShoppingViewModel by viewModels()


    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in [onSaveInstanceState]. **Note: Otherwise it is null.**
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        themePreferences = ThemePreference(this)
        var isDarkTheme by mutableStateOf(themePreferences.isDarkTheme())
        setContent {
            RecipeApplicationTheme(darkTheme = isDarkTheme) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomNavBar(apiViewModel, personalRecipeViewModel, shoppingViewModel, isDarkTheme = isDarkTheme,
                        onToggleTheme = {
                            isDarkTheme = it
                            themePreferences.setDarkTheme(isDarkTheme)
                        })
                }
            }
        }
        personalRecipeViewModel.fetchRandomRecipeOnAppStart()
    }
}