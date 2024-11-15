package com.example.chewsyui

import android.R
import android.view.LayoutInflater
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chewsyui.screens.AboutScreen
import com.example.chewsyui.screens.HomeScreen
import com.example.chewsyui.screens.SettingsScreen
import com.example.chewsyui.ui.addRecipe.AddRecipeScreen
import com.example.chewsyui.ui.addRecipe.AddRecipeViewModel
import com.example.chewsyui.ui.theme.ChewsyUITheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.chewsyui.ui.recipeB.RecipeScreen
import com.example.chewsyui.ui.recipeB.RecipeViewModel

enum class MainRoute(value: String) {
    Home("Home"),
    About("About"),
    Settings("Settings"),
    RecipeScreen("Recipe Book"),
    AddRecipe("Add Recipe")
}

private data class DrawerMenu(val icon: ImageVector, val title: String, val route: String)
private val menus = arrayOf(
    DrawerMenu(Icons.Filled.Face, "Home", MainRoute.Home.name),
    DrawerMenu(Icons.Filled.Settings, "Settings", MainRoute.Settings.name),
    DrawerMenu(Icons.Filled.Info, "About", MainRoute.About.name),
    DrawerMenu(Icons.Filled.Add, "Recipe Book", MainRoute.RecipeScreen.name)
)
@Composable
private fun DrawerContent(
    menus: Array<DrawerMenu>,
    onMenuClick: (String) -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ){
            Image(
                modifier = Modifier.size(150.dp),
                imageVector = Icons.Filled.AccountCircle,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        menus.forEach {
            NavigationDrawerItem(
                label ={ Text(text = it.title) },
                icon = { Icon(imageVector = it.icon, contentDescription = null) },
                selected = false,
                onClick = {
                    onMenuClick(it.route)
                }
            )
        }
    }
}

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    // Create a shared instance of RecipeViewModel at the navigation level
    val recipeViewModel = remember { RecipeViewModel() }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(menus) { route ->
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    navController.navigate(route)
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = MainRoute.Home.name) {
            composable(MainRoute.Home.name) {
                HomeScreen(drawerState)
            }
            composable(MainRoute.About.name) {
                AboutScreen(drawerState)
            }
            composable(MainRoute.Settings.name) {
                SettingsScreen(drawerState)
            }
            composable(MainRoute.RecipeScreen.name) {
                RecipeScreen(
                    recipeViewModel = recipeViewModel, // Pass the shared ViewModel
                    onAddRecipeClick = {
                        navController.navigate(MainRoute.AddRecipe.name)
                    }, drawerState
                )
            }
            composable(MainRoute.AddRecipe.name) {
                AddRecipeScreen(
                    recipeViewModel = recipeViewModel,  // Use the shared ViewModel
                    onRecipeAdded = {
                        navController.popBackStack(MainRoute.RecipeScreen.name, inclusive = false)
                    },
                    coroutineScope = coroutineScope
                )
            }
        }
    }
}
