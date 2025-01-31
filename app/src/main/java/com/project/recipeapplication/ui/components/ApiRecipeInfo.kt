package com.project.recipeapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.project.recipeapplication.R
import com.project.recipeapplication.viewModel.ApiRecipesViewModel
/**
 * Composable function for displaying details of a recipe fetched from an API.
 *
 * @param navController The navigation controller for navigating between composables.
 * @param viewModel The view model containing the logic for managing API recipes.
 */
@Composable
fun ApiRecipeInfo(navController: NavController, viewModel: ApiRecipesViewModel) {

    val recipe = viewModel.selectedRecipeDetails.collectAsState()
    val selectedRecipe = recipe.value
    var servingMultiplier by remember { mutableStateOf(selectedRecipe?.servings ?: 1) }

    SideEffect {
        println("Current Recipe Details: $recipe")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTopBar(
            title = "Recipe details" ,
            navController =  navController,
            showBackButton = true
        )

        if (selectedRecipe != null) {

            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {

                item { if (selectedRecipe.image.isNotBlank()) {
                    AsyncImage(
                        model = selectedRecipe.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clip(RoundedCornerShape(10.dp))
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.placeholder),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clip(RoundedCornerShape(10.dp))
                    )
                }

                    Text(modifier = Modifier.padding(10.dp),
                        text = selectedRecipe.title,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.primary)

                    Divider()

                    Row {
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            Text(text = "Ready in: ${selectedRecipe.readyInMinutes}", fontSize = 18.sp, modifier = Modifier.padding(8.dp), textAlign = TextAlign.Center)
                        }
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            Text(text = "no dish type", fontSize = 18.sp, modifier = Modifier.padding(8.dp), textAlign = TextAlign.Center)
                        }



                    }

                    Divider()

                    Text(text = "Serving size", fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp),color = MaterialTheme.colorScheme.primary)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        IconButton(onClick = { if (servingMultiplier > 1) servingMultiplier-- }) {
                            Icon(imageVector = Icons.Filled.RemoveCircle, contentDescription = "Remove icon")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "$servingMultiplier")
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(onClick = { servingMultiplier++ }) {
                            Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "Add icon",)
                        }
                    }

                }

                item {
                    Divider()
                    Text(text = "Ingredients", fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp), color = MaterialTheme.colorScheme.primary)
                }



                items(selectedRecipe.extendedIngredients) { ingredient ->
                    val adjustedAmount = ingredient.amount * servingMultiplier / selectedRecipe.servings
                    Row(modifier = Modifier
                        .padding(3.dp)
                        .align(Alignment.Start)) {
                        Text(modifier = Modifier.padding(3.dp), text = adjustedAmount.toString() ?: "No amount")
                        Text(modifier = Modifier.padding(3.dp), text = ingredient.unit ?: "No unit")
                        Text(modifier = Modifier.padding(3.dp), text = ingredient.name ?: "No ingredient name")
                    }
                }
                item { Divider()
                    Text(text = "Instructions", fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp), color = MaterialTheme.colorScheme.primary)}
                items(selectedRecipe.analyzedInstructions[0].steps) { instruction ->
                    Row(modifier = Modifier.padding(8.dp)) {
                        Text(text = instruction.number.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp), color = MaterialTheme.colorScheme.primary)
                        Text(text = instruction.step, modifier = Modifier.padding(3.dp),)
                    }
                }
                item { Divider()
                    Row {
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            Text(text = "Credits: ", fontSize = 18.sp, modifier = Modifier.padding(8.dp), textAlign = TextAlign.Center)
                        }
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            Text(text =  selectedRecipe.credits ?: "No credits", fontSize = 18.sp, modifier = Modifier.padding(8.dp), textAlign = TextAlign.Center)
                        }



                    }
                }

            }

        }

         else {
            CircularProgressIndicator()
        }

    }

}