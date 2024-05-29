package com.project.recipeapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.recipeapplication.data.model.database.PersonalRecipe
import com.project.recipeapplication.data.model.database.RecipeSummary
import com.project.recipeapplication.data.model.database.ShoppingItem
import com.project.recipeapplication.data.repository.PersonalRecipeRepository
import com.project.recipeapplication.data.repository.ShoppingListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShoppingViewModel() : ViewModel() {
    private val repository = ShoppingListRepository()
    private val _shoppinglist = MutableStateFlow<List<ShoppingItem>>(emptyList())
    val shoppinglist: StateFlow<List<ShoppingItem>> = _shoppinglist

    private val _editedItemId = MutableStateFlow<Int?>(null)
    val editedItemId: StateFlow<Int?> = _editedItemId

    private val _editedItemName = MutableStateFlow("")
    val editedItemName: StateFlow<String> = _editedItemName


    init {
        fetchShoppingList()
    }

    // function to fetch shoppinglist
    fun fetchShoppingList() {
        viewModelScope.launch {
            val result = repository.fetchShoppingList()
            _shoppinglist.value = result
        }
    }


    // function to add a new item to shoppinglist
    fun addNewItem(newItem: ShoppingItem) {



        viewModelScope.launch {
            repository.addShoppingItem(newItem)
            fetchShoppingList()
        }
    }

    fun updateCheckBox(item: ShoppingItem) {
        viewModelScope.launch {
            val updatedItem = item.copy(itemChecked = !item.itemChecked)
            repository.updateShoppingItem(updatedItem)
            fetchShoppingList()

        }
    }

    fun startEditing(item: ShoppingItem) {
        _editedItemId.value = item.id
        _editedItemName.value = item.name
    }

    fun updateEditedName(name: String) {
        _editedItemName.value = name
    }

    fun saveEditedItem() {
        val editedId = _editedItemId.value
        if (editedId != null) {
            viewModelScope.launch {
                val updatedItem = shoppinglist.value.find { it.id == editedId }?.copy(name = _editedItemName.value)
                if (updatedItem != null) {
                    repository.updateShoppingItem(updatedItem)
                    fetchShoppingList()
                }
            }
        }
        _editedItemId.value = null
        _editedItemName.value = ""
    }

    fun deleteShoppingItem(item: ShoppingItem) {
        viewModelScope.launch {
            repository.removeShoppingItem(item)
            fetchShoppingList()
        }
    }
}