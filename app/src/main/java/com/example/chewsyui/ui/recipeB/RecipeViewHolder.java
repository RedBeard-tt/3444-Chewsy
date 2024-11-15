package com.example.chewsyui.ui.recipeB;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chewsyui.R;
import com.example.chewsyui.ui.addRecipe.Recipe;

import java.util.List;

public class RecipeViewHolder extends RecyclerView.ViewHolder {
    private final TextView recipeName; // TextView for recipe name
    private final TextView ingredients; // TextView for ingredients
    private final TextView instructions; // TextView for instructions

    public RecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        recipeName = itemView.findViewById(R.id.recipeName);
        ingredients = itemView.findViewById(R.id.ingredientList);
        instructions = itemView.findViewById(R.id.instructionList);
    }

    public void bind(Recipe recipe) {
        recipeName.setText(recipe.getRecipeName());

        List<String> ingredientList = recipe.getIngredients();
        StringBuilder ingredientsStringBuilder = new StringBuilder();

        for (String ingredient : ingredientList) {
            ingredientsStringBuilder.append(ingredient).append("\n");
        }

        ingredients.setText(ingredientsStringBuilder.toString());

        instructions.setText(recipe.getInstructions());
    }
}