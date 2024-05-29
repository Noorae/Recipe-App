package com.project.recipeapplication.ui.components

import androidx.compose.runtime.Composable

//display shopping items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.project.recipeapplication.R
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel
import com.project.recipeapplication.viewModel.ShoppingViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ShoppingItems(viewModel: ShoppingViewModel) {
    val shoppingItems by viewModel.shoppinglist.collectAsState()
    val editedItemId by viewModel.editedItemId.collectAsState()
    val editedItemName by viewModel.editedItemName.collectAsState()

    LazyColumn(modifier = Modifier
        .padding(5.dp)) {
        items(shoppingItems, key = { item -> item.id}) { item ->
            ListItem(
                modifier = Modifier
                    .padding(vertical = 2.dp),
                colors = ListItemDefaults.colors(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)),
                headlineContent = { if (editedItemId == item.id) {
                    OutlinedTextField(
                        value = editedItemName,
                        onValueChange = { viewModel.updateEditedName(it) },
                        singleLine = true,
                        modifier = Modifier.padding(0.dp)
                    )
                } else {
                    if (item.itemChecked) {
                        Text(item.name, style = TextStyle(textDecoration = TextDecoration.LineThrough))
                    } else {
                        Text(item.name)
                    }

                } },
                leadingContent = {
                    Checkbox(checked = item.itemChecked, onCheckedChange = { viewModel.updateCheckBox(item)} )
                    },
                trailingContent = {
                    Row{
                        if (editedItemId == item.id) {
                            IconButton(onClick = { viewModel.saveEditedItem() }) {
                                Icon(imageVector = Icons.Filled.Check, contentDescription = "Save icon")
                            }
                        } else {
                            IconButton(onClick = { viewModel.startEditing(item) }) {
                                Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit icon")
                            }
                        }
                        IconButton(onClick = { viewModel.deleteShoppingItem(item) })
                        {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Icon to delete item")
                        }

                    }

                }
            )

        }
    }
}