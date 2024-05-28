package com.project.recipeapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.recipeapplication.BuildConfig
import com.project.recipeapplication.data.model.api.ApiDetailedRecipe
import com.project.recipeapplication.data.model.api.ApiRecipe
import com.project.recipeapplication.data.repository.ApiRecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ApiRecipesViewModel : ViewModel() {
    private val repository = ApiRecipeRepository()
    private val _results : SnapshotStateList<ApiRecipe> = mutableStateListOf<ApiRecipe>()
    val recipes : List<ApiRecipe> get() = _results
    var searchQuery by mutableStateOf("")

    // searched recipes details are null in the start, with optional
    //var selectedRecipeDetails by mutableStateOf<ApiDetailedRecipe?>(null)
    var selectedId by mutableIntStateOf(0)

    private var _selectedRecipeDetails = MutableStateFlow<ApiDetailedRecipe?>(null)
    val selectedRecipeDetails: StateFlow<ApiDetailedRecipe?> get() = _selectedRecipeDetails



    //function to take a new searchQuery
    fun updateSearchQuery(newQuery: String) {
        searchQuery = newQuery
    }

    //function that fetches a list of recipes matching search terms
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

    //function that fetches detailed recipe data
    fun fetchRecipeDetails(recipeId: Int) {
        val apiKey = BuildConfig.API_KEY


        viewModelScope.launch {
            val details = repository.fetchRecipeDetails(
                recipeId,
                apiKey
            )
            _selectedRecipeDetails.value = details

            println(selectedRecipeDetails)
            println(_selectedRecipeDetails.value!!.analyzedInstructions)


        }

    }


}
