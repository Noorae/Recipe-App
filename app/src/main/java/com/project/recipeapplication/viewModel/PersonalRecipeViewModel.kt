package com.project.recipeapplication.viewModel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.recipeapplication.BuildConfig
import com.project.recipeapplication.data.model.api.ApiDetailedRecipe
import com.project.recipeapplication.data.model.database.Ingredient
import com.project.recipeapplication.data.model.database.InstructionStep
import com.project.recipeapplication.data.model.database.PersonalRecipe
import com.project.recipeapplication.data.model.database.RecipeSummary
import com.project.recipeapplication.data.model.database.RecipeWithFullData
import com.project.recipeapplication.data.model.database.Tag
import com.project.recipeapplication.data.repository.PersonalRecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PersonalRecipeViewModel(): ViewModel() {
    private val repository = PersonalRecipeRepository()
    private val _personalRecipes = MutableStateFlow<List<RecipeSummary>>(emptyList())
    val personalRecipes: StateFlow<List<RecipeSummary>> = _personalRecipes

    //selected Personal recipe data
    private var _selectedRecipeDetails = MutableStateFlow<RecipeWithFullData?>(null)
    val selectedRecipeDetails: StateFlow<RecipeWithFullData?> get() = _selectedRecipeDetails

    //selected id data
    var selectedId by mutableIntStateOf(0)

    //recipe data
    var recipeTitle by mutableStateOf("")
    var recipeImagePath by mutableStateOf<String?>(null)
    var recipeReady by mutableStateOf("")
    var recipeMealType by mutableStateOf("")
    var recipeServingSize by mutableStateOf(0)
    var recipeIsFavorite by mutableStateOf(false)
    var recipeInstructions = mutableStateListOf<InstructionStep>()
    var recipeIngredients = mutableStateListOf<Ingredient>()
    var recipeTags = mutableStateListOf<Tag>()

    //ingredients data
    var ingredientAmount by mutableStateOf(0.0)
    var ingredientUnit by mutableStateOf("")
    var ingredientName by mutableStateOf("")

    //Instruction step data
    var instructionDescription by mutableStateOf("")

    //tag data
    var tagDescription by mutableStateOf("")


    init {
        fetchRecipes()
    }

    // function to fetch recipe summary for all recipes
    fun fetchRecipes() {
        viewModelScope.launch {
            val recipes = repository.fetchRecipes()
            _personalRecipes.value = recipes
        }
    }

    // function to add a new recipe
    private fun addNewRecipe(newRecipe: PersonalRecipe) {
        viewModelScope.launch {
            repository.addRecipe(
                newRecipe,
                recipeIngredients.toList(),
                recipeInstructions.toList(),
                recipeTags.toList()
            )
            fetchRecipes()
        }
    }


    //helper function to send new recipes
    fun collectRecipeData() {

        val newRecipe = PersonalRecipe(
            title = recipeTitle,
            imagePath = recipeImagePath ?: "",
            readyInMinutes = recipeReady,
            mealType = recipeMealType,
            servingSize = recipeServingSize,
            isFavorite = recipeIsFavorite
        )



        addNewRecipe(newRecipe)
        clearAllFields()

    }

    //Helper function to clean all fields
    private fun clearAllFields() {
        recipeTitle = ""
        recipeImagePath = null
        recipeReady = ""
        recipeMealType = ""
        recipeServingSize = 0
        recipeInstructions.clear()
        recipeTags.clear()
        recipeIngredients.clear()
    }

    //fetch selected recipe data
    fun fetchRecipeDetails(recipeId: Int) {
        viewModelScope.launch {
            val details = repository.getRecipeWithFullData(recipeId)
            _selectedRecipeDetails.value = details

            println(selectedRecipeDetails)


        }

    }


    //function to delete recipe by id
    fun deleteRecipeById(recipeId : Int) {
        viewModelScope.launch {
            repository.deleteRecipeById(recipeId)
            fetchRecipes()

        }
    }

    fun addIngredient() {
            recipeIngredients.add(
                Ingredient(
                    recipeId = 0,
                    amount = ingredientAmount,
                    unit = ingredientUnit,
                    name = ingredientName
                )
            )


        ingredientAmount = 0.0
        ingredientUnit = ""
        ingredientName = ""
    }

    fun addInstruction() {
            recipeInstructions.add(
                InstructionStep(
                    recipeId = 0,
                    stepNumber = recipeInstructions.size + 1,
                    description = instructionDescription
                )
            )



        instructionDescription = ""
    }

    fun addTag() {
            recipeTags.add(
                Tag(
                    recipeId = 0,
                    tagName = tagDescription
                )
            )


        tagDescription = ""
        println(recipeTags)
    }

    // Methods for permissions
    fun setImageUri(context: Context, uri: Uri?) {
        uri?.let {
            context.contentResolver.takePersistableUriPermission(
                it,
                Intent.FLAG_GRANT_READ_URI_PERMISSION)
            recipeImagePath = it.toString()
        }
    }
}