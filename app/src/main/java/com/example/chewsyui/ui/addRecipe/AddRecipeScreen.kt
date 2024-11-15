// File: com/example/chewsyui/ui/addRecipe/AddRecipeScreen.kt

package com.example.chewsyui.ui.addRecipe

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chewsyui.ui.addRecipe.AddRecipeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import android.widget.Toast
import com.example.chewsyui.ui.recipeB.RecipeViewModel
import androidx.compose.ui.platform.LocalContext

@Composable
fun AddRecipeScreen(
    recipeViewModel: RecipeViewModel,
    onRecipeAdded: () -> Unit,
    coroutineScope: CoroutineScope
) {
    var recipeName by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf("") }
    var instructions by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(text = "Add New Recipe", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = recipeName,
            onValueChange = { recipeName = it },
            label = { Text("Recipe Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = ingredients,
            onValueChange = { ingredients = it },
            label = { Text("Ingredients (comma-separated)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = instructions,
            onValueChange = { instructions = it },
            label = { Text("Instructions") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (recipeName.isNotBlank() && ingredients.isNotBlank() && instructions.isNotBlank()) {
                    val ingredientsList = ingredients.split(",").map { it.trim() }
                    coroutineScope.launch {
                        recipeViewModel.addRecipe(Recipe(recipeName, ingredientsList, instructions))
                        Toast.makeText(context, "Recipe added successfully!", Toast.LENGTH_SHORT).show()
                        onRecipeAdded()
                    }
                } else {
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Recipe")
        }
    }
}