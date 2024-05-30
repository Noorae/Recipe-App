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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.AssistChip

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.Color
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
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel

@Composable
fun PersonalRecipeInfo(navController: NavController, viewModel: PersonalRecipeViewModel) {
    val recipe = viewModel.selectedRecipeDetails.collectAsState()
    val selectedRecipe = recipe.value
    var servingMultiplier by remember { mutableStateOf(selectedRecipe?.recipe?.servingSize ?: 1) }


    SideEffect {
        println("Current Recipe Details: $selectedRecipe")
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

                item { if (selectedRecipe.recipe.imagePath.isNotBlank()) {
                    AsyncImage(
                        model = selectedRecipe.recipe.imagePath,
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
                        text = selectedRecipe.recipe.title,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.primary)

                    Divider()

                    Row {
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            Text(text = "Ready in: ${selectedRecipe.recipe.readyInMinutes}", fontSize = 18.sp, modifier = Modifier.padding(8.dp), textAlign = TextAlign.Center)
                        }
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            Text(text = "${selectedRecipe.recipe.mealType}", fontSize = 18.sp, modifier = Modifier.padding(8.dp), textAlign = TextAlign.Center)
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

                    Row(modifier = Modifier.padding(8.dp)) {
                        selectedRecipe.tags.forEach { tag ->
                            AssistChip(modifier = Modifier.padding(4.dp), onClick = { println(" tag name: ${tag.tagName}") }, label = {Text(text = tag.tagName)})
                        }
                    }
                }
                
                item {
                    Divider()
                    Text(text = "Ingredients", fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp), color = MaterialTheme.colorScheme.primary)
                }



                items(selectedRecipe.ingredients) { ingredient ->
                    val adjustedAmount = ingredient.amount * servingMultiplier / selectedRecipe.recipe.servingSize
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
                items(selectedRecipe.instructions) { instruction ->
                    Row(modifier = Modifier.padding(8.dp)) {
                        Text(text = instruction.stepNumber.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp), color = MaterialTheme.colorScheme.primary)
                        Text(text = instruction.description, modifier = Modifier.padding(3.dp),)
                    }
                }

            }

        } else {
            CircularProgressIndicator()
        }

    }

}