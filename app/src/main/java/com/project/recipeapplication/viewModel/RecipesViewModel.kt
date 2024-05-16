package com.project.recipeapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    var searchQuery by mutableStateOf("")


    //function to take a new searchQuery
    fun updateSearchQuery(newQuery: String) {
        searchQuery = newQuery
    }

    fun fetchRecipes() {
        val apiKey = BuildConfig.API_KEY

        //TODO change hard coded parameters into variables
        viewModelScope.launch {
            val recipeList = repository.fetchRecipes(
                searchQuery,
                apiKey,
                3,
                true
            )
            //empty the search results after every search
            _results.clear()
            //populate the results with new fetched recipes
            _results.addAll(recipeList)
            //TODO remove debugging logs
            println(_results.joinToString(separator = "\n") { it.toString() })
        }

    }

}
