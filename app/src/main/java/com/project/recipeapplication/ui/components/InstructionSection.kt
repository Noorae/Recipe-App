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
fun InstructionsSection(viewModel: PersonalRecipeViewModel) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(
            text = "Recipe Instructions",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        viewModel.recipeInstructions.forEachIndexed { index, instruction ->
            Row {
                OutlinedTextField(
                    value = instruction.description,
                    onValueChange = {
                        viewModel.recipeInstructions[index] =
                            instruction.copy(description = it)
                    },
                    label = { Text("Step ${index + 1}", style = TextStyle(fontSize = 14.sp)) },
                    modifier = Modifier.weight(1f).padding(2.dp)
                )
            }
        }

        Button(onClick = { viewModel.addInstruction() }) {
            Text(text = "+")
        }
    }
}