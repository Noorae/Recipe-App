package com.project.recipeapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.recipeapplication.R
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel

@Composable
fun RecipeList(modifier: Modifier = Modifier, viewModel: PersonalRecipeViewModel) {
    val recipeState = viewModel.personalRecipes.collectAsState()
    val recipes = recipeState.value

    LazyColumn(modifier = Modifier
        .padding(5.dp)) {
        items(recipes) { recipe ->
            ListItem(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = ListItemDefaults.colors(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)),
                headlineContent = { Text(recipe.title) },
                leadingContent = {
                    Image(painter = painterResource(id = R.drawable.placeholder),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .size(100.dp)
                            .clip(RoundedCornerShape(10.dp)))
                },
                trailingContent = {
                    IconButton(
                        onClick = {
                            println("deletebutton with recipe id ${recipe.id} clicked")
                            viewModel.deleteRecipeById(recipe.id)

                        }

                ) {Icon(imageVector = Icons.Filled.Delete, contentDescription = "Icon to display more options")}})

        }
    }
}