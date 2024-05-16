package com.project.recipeapplication.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeSearchBar(searchQuery: String,
                    onQueryChange: (String) -> Unit,
                    onSearch: () -> Unit,
                    modifier: Modifier = Modifier) {

    val coroutineScope = rememberCoroutineScope()

    //TODO MODULIZE SEARCHBAR TO BE USED ON RECIPEBANK
    SearchBar(
        modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 8.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },
        query = searchQuery ,
        onQueryChange = { newQuery ->
            onQueryChange(newQuery)
            if (newQuery.length >= 3) {
                coroutineScope.launch {
                    delay(2000)
                    onSearch()
                }
            } },
        onSearch = { if (searchQuery.length >= 3) onSearch() },
        active = false,
        onActiveChange = {/*TODO*/}) {

    }

}