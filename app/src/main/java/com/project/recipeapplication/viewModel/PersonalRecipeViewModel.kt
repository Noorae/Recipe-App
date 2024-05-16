package com.project.recipeapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    //TODO DELETE THIS HELPER FUNCTION
    fun addMockRecipe(recipe : PersonalRecipe) {
        viewModelScope.launch {
            repository.addRecipe(recipe)
            fetchRecipes()
        }
    }




}