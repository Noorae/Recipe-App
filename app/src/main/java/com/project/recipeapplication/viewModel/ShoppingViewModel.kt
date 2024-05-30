package com.project.recipeapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.recipeapplication.data.model.database.ShoppingItem
import com.project.recipeapplication.data.repository.ShoppingListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the shopping list in the application.
 */
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

    /**
     * Fetches the shopping list from the repository.
     */
    fun fetchShoppingList() {
        viewModelScope.launch {
            val result = repository.fetchShoppingList()
            _shoppinglist.value = result
        }
    }


    /**
     * Adds a new item to the shopping list.
     *
     * @param newItem The item to be added.
     */
    fun addNewItem(newItem: ShoppingItem) {

        viewModelScope.launch {
            repository.addShoppingItem(newItem)
            fetchShoppingList()
        }
    }

    /**
     * Updates the checkbox state of a shopping item.
     *
     * @param item The item to be updated.
     */
    fun updateCheckBox(item: ShoppingItem) {
        viewModelScope.launch {
            val updatedItem = item.copy(itemChecked = !item.itemChecked)
            repository.updateShoppingItem(updatedItem)
            fetchShoppingList()

        }
    }

    /**
     * Starts editing a shopping item.
     *
     * @param item The item to be edited.
     */
    fun startEditing(item: ShoppingItem) {
        _editedItemId.value = item.id
        _editedItemName.value = item.name
    }

    /**
     * Updates the name of the item being edited.
     *
     * @param name The new name for the item.
     */
    fun updateEditedName(name: String) {
        _editedItemName.value = name
    }

    /**
     * Saves the edited item.
     */
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

    /**
     * Deletes a shopping item from the list.
     *
     * @param item The item to be deleted.
     */
    fun deleteShoppingItem(item: ShoppingItem) {
        viewModelScope.launch {
            repository.removeShoppingItem(item)
            fetchShoppingList()
        }
    }
}