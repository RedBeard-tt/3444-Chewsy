package com.example.chewsyui.ui.editRecipe

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.chewsyui.ui.addRecipe.Recipe
import com.example.chewsyui.ui.recipeB.RecipeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun EditRecipeScreen(
    recipe: Recipe, // Pass the selected recipe to be edited
    recipeViewModel: RecipeViewModel,
    onRecipeUpdated: () -> Unit,
    coroutineScope: CoroutineScope
) {
    var recipeName by remember { mutableStateOf(recipe.recipeName) }
    var ingredients by remember { mutableStateOf(recipe.ingredients.joinToString(", ")) }
    var instructions by remember { mutableStateOf(recipe.instructions) }
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(text = "Edit Recipe", style = MaterialTheme.typography.titleLarge)
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
                        // Use the ViewModel to update the recipe
                        recipeViewModel.editRecipe(
                            recipe.copy(
                                recipeName = recipeName,
                                ingredients = ingredientsList,
                                instructions = instructions
                            )
                        )
                        Toast.makeText(context, "Recipe updated successfully!", Toast.LENGTH_SHORT).show()
                        onRecipeUpdated()
                    }
                } else {
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Changes")
        }
    }
}
