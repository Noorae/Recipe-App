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
/**
 * ViewModel class responsible for managing personal recipe data.
 */
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

    /**
     * Initializes the ViewModel by fetching all personal recipes.
     */
    init {
        fetchRecipes()
    }

    /**
     * Fetches all personal recipes.
     */
    fun fetchRecipes() {
        viewModelScope.launch {
            val recipes = repository.fetchRecipes()
            _personalRecipes.value = recipes
        }
    }

    /**
     * Adds a new personal recipe.
     */
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


    /**
     * Collects data for a new recipe and adds it to the repository.
     */
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

    /**
     * Clears all fields related to recipe data.
     */
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

    /**
     * Fetches detailed information for a specific recipe.
     *
     * @param recipeId The ID of the recipe.
     */
    fun fetchRecipeDetails(recipeId: Int) {
        viewModelScope.launch {
            val details = repository.getRecipeWithFullData(recipeId)
            _selectedRecipeDetails.value = details

            println(selectedRecipeDetails)


        }

    }

    /**
     * Fetches data for a random recipe.
     */
    fun fetchRandomRecipeData() {
        viewModelScope.launch {
            val details = repository.getRandomRecipe()
            _selectedRecipeDetails.value = details

            println(selectedRecipeDetails)

        }

    }

    /**
     * Fetches data for a random recipe when the app starts.
     */
    fun fetchRandomRecipeOnAppStart() {
        fetchRandomRecipeData()
    }

    /**
     * Fetches data for the newest recipe.
     */
    fun fetchNewestRecipeData() {
        viewModelScope.launch {
            val details = repository.getNewestRecipe()
            _selectedRecipeDetails.value = details

            println(selectedRecipeDetails)

        }

    }

    /**
     * Deletes a recipe by its ID.
     *
     * @param recipeId The ID of the recipe to delete.
     */
    fun deleteRecipeById(recipeId : Int) {
        viewModelScope.launch {
            repository.deleteRecipeById(recipeId)
            fetchRecipes()

        }
    }
    /**
     * Adds an ingredient to the recipe.
     */
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

    /**
     * Adds an instruction step to the recipe.
     */
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

    /**
     * Adds a tag to the recipe.
     */
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

    /**
     * Sets the image URI for the recipe, ensuring that the app has the necessary
     * permissions to read the URI.
     *
     * @param context The context used to get the content resolver.
     * @param uri The URI of the image to be set. If null, the image path is not
     * updated.
     */
    fun setImageUri(context: Context, uri: Uri?) {
        uri?.let {
            context.contentResolver.takePersistableUriPermission(
                it,
                Intent.FLAG_GRANT_READ_URI_PERMISSION)
            recipeImagePath = it.toString()
        }
    }
}