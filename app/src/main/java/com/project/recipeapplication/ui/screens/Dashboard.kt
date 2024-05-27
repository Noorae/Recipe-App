package com.project.recipeapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.recipeapplication.R
import com.project.recipeapplication.ui.components.CustomTopBar

@Composable
fun Dashboard(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTopBar(
            title = "Dashboard" ,
            navController =  navController
        )
        Divider()
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier
                .size(width = 400.dp, height = 350.dp)
                .padding(15.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(painter = painterResource(id = R.drawable.placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
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
                        text = "Päivän reseptisuositus",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .padding(end = 8.dp),
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = "Makaronilaatikko",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .padding( end = 8.dp),
                        textAlign = TextAlign.End
                    )

                }



            }


        }
        Divider()
        Row {
            ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier
                    .size(width = 180.dp, height = 150.dp)
                    .padding(15.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

            ) {
                Text(
                text = " Omat Suosikit",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.outlineVariant,
                modifier = Modifier
                    .padding(8.dp),
                )
            }
            ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier
                    .size(width = 180.dp, height = 150.dp)
                    .padding(15.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

            ) {
                Text(
                    text = "Tallennetut inspiraatiot",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier
                        .padding(8.dp),
                )
            }

        }
        Divider()


    }


}