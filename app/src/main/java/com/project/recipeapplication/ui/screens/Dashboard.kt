package com.project.recipeapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.project.recipeapplication.R
import com.project.recipeapplication.ui.components.CustomSettingsTopBar
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel

@Composable
fun Dashboard(navController: NavController, isDarkTheme: Boolean, onToggleTheme: (Boolean) -> Unit, viewModel: PersonalRecipeViewModel) {
    val recipe = viewModel.selectedRecipeDetails.collectAsState()
    val selectedRecipe = recipe.value

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomSettingsTopBar(
            title = "Dashboard" ,
            navController =  navController,
            isDarkTheme = isDarkTheme,
            onToggleTheme = onToggleTheme
        )
        Divider()
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier
                .size(width = 400.dp, height = 350.dp)
                .padding(15.dp)
                .clickable(
                    onClick = {
                        navController.navigate("personalRecipeInfo")
                    },
                ),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                if (selectedRecipe != null) {
                    if (selectedRecipe.recipe.imagePath.isNotBlank()) {
                        AsyncImage(
                            model = selectedRecipe.recipe.imagePath,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(RoundedCornerShape(10.dp))
                        )
                    } else {
                        Image(painter = painterResource(id = R.drawable.placeholder),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.4f))
                )
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Recommended",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                        modifier = Modifier
                            .padding(end = 8.dp),
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = selectedRecipe?.recipe?.title ?: "",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White,
                        modifier = Modifier.padding( end = 8.dp),
                        textAlign = TextAlign.End
                    )

                }



            }


        }
        Divider()
        Text(text = "Quick access")
        Row {
            Box(modifier = Modifier.weight(1f)) {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                    onClick = { navController.navigate("addRecipe") }) {
                    Text(text = "Add new recipe")
                }

            }
            Box(modifier = Modifier.weight(1f)) {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),onClick = { navController.navigate("groceries") }) {
                    Text(text = "Shopping List")
                }

            }

        }
        Row {
            Box(modifier = Modifier.weight(1f)) {
                Button( modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp), onClick = { /*TODO*/ }) {
                    Text(text = "Saved Inspirations")
                }

            }
            Box(modifier = Modifier.weight(1f)) {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),onClick = { /*TODO*/ }) {
                    Text(text = "Newest recipe")
                }

            }

        }
        Divider()
        Box(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)) {
            Card(modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()) {
                Text(text= "Recipe Stats")

            }
        }



    }


}