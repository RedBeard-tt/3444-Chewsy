package com.example.chewsyui.ui.recipeB

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.chewsyui.CustomAppBar
import com.example.chewsyui.MainRoute
import com.example.chewsyui.ui.addRecipe.Recipe


@Composable
fun RecipeScreen(
    recipeViewModel: RecipeViewModel,
    onAddRecipeClick: () -> Unit, drawerState: DrawerState,
    navController: NavHostController
) {
    var selectedRecipe by remember { mutableStateOf<Recipe?>(null) }
    var showOptions by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {CustomAppBar(drawerState = drawerState, title = "Recipe Book")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val recipes by recipeViewModel.recipeBook.observeAsState(initial = emptyList())
            // List of recipes
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                items(recipes) { recipe ->
                    RecipeItem(recipe = recipe) {
                        selectedRecipe = recipe
                        showOptions = true
                    }
                }
            }

            if(showOptions && selectedRecipe != null){
                RecipeOptionsDialog(
                    recipe = selectedRecipe!!,
                    onEdit = { recipe ->
                        navController.currentBackStackEntry?.savedStateHandle?.set("recipe", recipe)
                        navController.navigate(MainRoute.EditRecipeScreen.name)
                        showOptions = false
                    }
                    ,
                    onRemove = { recipe ->
                        recipeViewModel.removeRecipe(recipe)
                        showOptions = false
                    },
                    onDismiss = {
                        showOptions = false
                    }
                )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeOptionsDialog(
    recipe: Recipe,
    onEdit: (Recipe) -> Unit,
    onRemove: (Recipe) -> Unit,
    onDismiss: () -> Unit
) {
    BasicAlertDialog(onDismissRequest = { onDismiss() },
        content = {
            Surface(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text("Recipe Options", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("What would you like to do with '${recipe.recipeName}'?")
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                onEdit(recipe);
                            },
                            modifier = Modifier
                                .padding(horizontal = 2.dp)
                                .widthIn(min = 20.dp)
                                .height(48.dp)
                        ) {
                            Text("Edit")
                        }
                        Button(
                            onClick = { onRemove(recipe) },
                            modifier = Modifier
                                .padding(horizontal = 2.dp)
                                .widthIn(min = 20.dp)
                                .height(48.dp)
                        ) {
                            Text("Remove")
                        }
                        Button(
                            onClick = { onDismiss() },
                            modifier = Modifier
                                .padding(horizontal = 2.dp)
                                .widthIn(min = 20.dp)
                                .height(48.dp)
                        ) {
                            Text("Cancel")
                        }
                    }
                }
            }
        }
    )
}
