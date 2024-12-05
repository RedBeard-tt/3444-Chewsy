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
            Recipe(
                "Pasta",
                listOf("Pasta", "Tomato Sauce", "Cheese"),
                "Boil the pasta and mix with sauce."
            ),
            Recipe(
                "Salad",
                listOf("Lettuce", "Tomato", "Cucumber", "Olive Oil"),
                "Chop all ingredients and mix."
            )
        )
        _recipeBook.value = initialRecipes
    }

    // Function to add a recipe
    fun addRecipe(recipe: Recipe) {
        val currentRecipes = _recipeBook.value?.toMutableList() ?: mutableListOf()
        currentRecipes.add(recipe)
        _recipeBook.value = currentRecipes
    }

    // Function to edit a recipe (update an existing recipe)
    fun editRecipe(updatedRecipe: Recipe) {
        val currentRecipes = _recipeBook.value?.toMutableList() ?: mutableListOf()
        val index = currentRecipes.indexOfFirst { it.recipeName == updatedRecipe.recipeName }
        if (index != -1) {
            currentRecipes[index] = updatedRecipe
            _recipeBook.value = currentRecipes
        }
    }

    // Function to remove a recipe
    fun removeRecipe(recipeToRemove: Recipe) {
        val currentRecipes = _recipeBook.value?.toMutableList() ?: mutableListOf()
        currentRecipes.remove(recipeToRemove)
        _recipeBook.value = currentRecipes
    }
}

