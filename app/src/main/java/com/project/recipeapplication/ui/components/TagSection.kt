package com.project.recipeapplication.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel

@Composable
fun TagSection(viewModel: PersonalRecipeViewModel) {

    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(
            text = "Tags",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        val tags = viewModel.recipeTags
        val lastIndex = tags.size - 1
        tags.forEachIndexed { index, tag ->
            Row {
                OutlinedTextField(
                    value = tag.tagName,
                    onValueChange = {
                        viewModel.recipeTags[index] =
                            tag.copy(tagName = it)
                    },
                    label = { Text("Tag", style = TextStyle(fontSize = 14.sp)) },
                    modifier = Modifier.weight(1f).padding(2.dp)
                )
            }

        }

        Button(onClick = { viewModel.addTag() }) {
            Text(text = "+")
        }
    }
}

