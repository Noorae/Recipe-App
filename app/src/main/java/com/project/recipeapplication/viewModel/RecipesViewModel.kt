package com.project.recipeapplication.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.recipeapplication.BuildConfig
import com.project.recipeapplication.data.model.Recipe
import com.project.recipeapplication.data.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {
    private val repository = RecipeRepository()
    private val _results : SnapshotStateList<Recipe> = mutableStateListOf<Recipe>()
    val recipes : List<Recipe> get() = _results

    fun fetchRecipes(searchQuery : String) {
        val apiKey = BuildConfig.API_KEY

        viewModelScope.launch {
            val recipeList = repository.fetchRecipes(
                searchQuery,
                apiKey,
                3,
                true
            )

            _results.addAll(recipeList)
            println(_results.joinToString(separator = "\n") { it.toString() })
        }

    }

}
