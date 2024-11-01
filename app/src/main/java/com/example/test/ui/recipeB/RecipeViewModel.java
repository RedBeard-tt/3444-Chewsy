package com.example.test.ui.recipeB;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.ui.addRecipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeViewModel extends ViewModel {
    private final MutableLiveData<List<Recipe>> recipeBook;

    public RecipeViewModel() {
        recipeBook = new MutableLiveData<>(new ArrayList<>());

        // Initialize with some default recipes
        List<Recipe> initialRecipes = new ArrayList<>();
        initialRecipes.add(new Recipe("Pasta", List.of("Pasta", "Tomato Sauce", "Cheese"), "Boil the pasta and mix with sauce."));
        initialRecipes.add(new Recipe("Salad", List.of("Lettuce", "Tomato", "Cucumber", "Olive Oil"), "Chop all ingredients and mix."));

        recipeBook.setValue(initialRecipes);
    }

    public LiveData<List<Recipe>> getRecipeBook() {
        return recipeBook;
    }

    public void addRecipeBook(Recipe recipe) {
        List<Recipe> currentRecipes = recipeBook.getValue();
        if (currentRecipes != null) {
            // Create a new list to trigger LiveData update
            List<Recipe> updatedRecipes = new ArrayList<>(currentRecipes);
            updatedRecipes.add(recipe);
            recipeBook.setValue(updatedRecipes);
        }
    }
}