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
import com.project.recipeapplication.data.model.database.ApiFavoriteRecipe
import com.project.recipeapplication.data.repository.ApiRecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel class responsible for managing API recipe data.
 */
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

    //saved favorite recipes
    private val _apiFavoriteRecipes: SnapshotStateList<ApiFavoriteRecipe> = mutableStateListOf()
    val apiFavoriteRecipes: List<ApiFavoriteRecipe> get() = _apiFavoriteRecipes

    /**
     * Initializes the ViewModel by fetching favorite recipes.
     */
    init {
        fetchFavoriteRecipes()
    }

    /**
     * Updates the search query for recipe search.
     *
     * @param newQuery The new search query.
     */
    fun updateSearchQuery(newQuery: String) {
        searchQuery = newQuery
    }

    /**
     * Fetches a list of recipes matching the search query.
     */
    fun fetchRecipes() {
        val apiKey = BuildConfig.API_KEY

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

    /**
     * Fetches detailed information for a specific recipe.
     *
     * @param recipeId The ID of the recipe.
     */
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

    /**
     * Fetches favorite recipes.
     */
    private fun fetchFavoriteRecipes() {
        viewModelScope.launch {
            val recipes = repository.fetchFavoriteRecipes()
            _apiFavoriteRecipes.clear()
            _apiFavoriteRecipes.addAll(recipes)
        }
    }

    /**
     * Adds a recipe to favorites.
     *
     * @param recipe The recipe to add to favorites.
     */
    fun addToFavorites(recipe: ApiFavoriteRecipe) {
        viewModelScope.launch {
            repository.addRecipeToFavorites(recipe)
            fetchFavoriteRecipes()
        }
    }

    /**
     * Deletes a recipe from favorites.
     *
     * @param recipe The recipe to delete from favorites.
     */
    fun deleteFromFavorites(recipe: ApiFavoriteRecipe) {
        viewModelScope.launch {
            repository.deleteFromFavorites(recipe)
            fetchFavoriteRecipes()
        }
    }

    /**
     * Checks if a recipe with the specified ID is in favorites.
     *
     * @param recipeId The ID of the recipe to check.
     * @return `true` if the recipe is in favorites, `false` otherwise.
     */
    fun isFavorite(recipeId: Int): Boolean {
        return _apiFavoriteRecipes.any { it.apiId == recipeId }
    }


}
