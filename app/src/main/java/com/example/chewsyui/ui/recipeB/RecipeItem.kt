package com.example.chewsyui.ui.recipeB

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chewsyui.ui.addRecipe.Recipe

@Composable
fun RecipeItem(recipe: Recipe, onClick: (Recipe) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick(recipe) }, // Make the Card clickable
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = recipe.recipeName, fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Ingredients:",
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
            Text(text = recipe.ingredients.joinToString(separator = "\n"))

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Instructions:",
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
            Text(text = recipe.instructions)
        }
    }
}
