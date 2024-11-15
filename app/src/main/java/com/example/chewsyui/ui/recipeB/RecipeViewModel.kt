// File: com/example/chewsyui/ui/recipeB/RecipeViewModel.kt

package com.example.chewsyui.ui.recipeB

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chewsyui.ui.addRecipe.Recipe

class RecipeViewModel : ViewModel() {

    private val _recipeBook = MutableLiveData<List<Recipe>>()
    val recipeBook: LiveData<List<Recipe>> = _recipeBook

    init {
        val initialRecipes = mutableListOf(
            Recipe("Pasta", listOf("Pasta", "Tomato Sauce", "Cheese"), "Boil the pasta and mix with sauce."),
            Recipe("Salad", listOf("Lettuce", "Tomato", "Cucumber", "Olive Oil"), "Chop all ingredients and mix.")
        )
        _recipeBook.value = initialRecipes
    }

    // Function to add a recipe
    public fun addRecipe(recipe: Recipe) {
        val currentRecipes = _recipeBook.value?.toMutableList() ?: mutableListOf()
        currentRecipes.add(recipe)
        _recipeBook.value = currentRecipes
    }
}