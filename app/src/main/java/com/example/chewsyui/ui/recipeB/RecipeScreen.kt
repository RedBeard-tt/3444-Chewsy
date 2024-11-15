package com.example.chewsyui.ui.recipeB

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chewsyui.CustomAppBar
import com.example.chewsyui.ui.addRecipe.Recipe


@Composable
fun DrawerContent(menus: Any, content: Any) {

}

@Composable
fun RecipeScreen(
    recipeViewModel: RecipeViewModel,
    onAddRecipeClick: () -> Unit
) {
    val recipes by recipeViewModel.recipeBook.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            CustomAppBar(drawerState = rememberDrawerState(DrawerValue.Closed), title = "Recipe Book")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // List of recipes
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                items(recipes) { recipe ->
                    RecipeItem(recipe)
                }
            }

            // Floating action button for adding recipes
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                FloatingActionButton(
                    onClick = onAddRecipeClick,
                    content = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Recipe"
                        )
                    }
                )
            }
        }
    }
}
