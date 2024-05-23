package com.project.recipeapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.recipeapplication.R
import com.project.recipeapplication.data.model.PersonalRecipe
import com.project.recipeapplication.data.repository.ApiRecipeRepository
import com.project.recipeapplication.data.repository.PersonalRecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PersonalRecipeViewModel(): ViewModel() {
    private val repository = PersonalRecipeRepository()
    private val _personalRecipes = MutableStateFlow<List<PersonalRecipe>>(emptyList())
    val personalRecipes: StateFlow<List<PersonalRecipe>> = _personalRecipes

    // //TODO maybe move to a better place
    var recipeTitle by mutableStateOf("")
    var recipeInstructions by mutableStateOf("")


    init {
        fetchRecipes()
    }

    fun fetchRecipes() {
        viewModelScope.launch {
            val recipes = repository.fetchRecipes()
            _personalRecipes.value = recipes
        }
        println(personalRecipes)
    }

    //TODO MODIFY THIS HELPER FUNCTION
    private fun addMockRecipe(recipe: PersonalRecipe) {
        viewModelScope.launch {
            repository.addRecipe(recipe)
            fetchRecipes()
        }
    }

    fun collectRecipeData() {
        val newRecipe = PersonalRecipe(
            title = recipeTitle,
            instructions = recipeInstructions,
            imagePath = "will be added later"
        )
        addMockRecipe(newRecipe)
    }

}