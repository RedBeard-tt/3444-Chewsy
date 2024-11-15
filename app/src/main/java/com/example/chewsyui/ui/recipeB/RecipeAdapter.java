package com.example.chewsyui.ui.recipeB;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chewsyui.R;
import com.example.chewsyui.ui.addRecipe.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<Recipe> recipeList;

    public RecipeAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_view, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

   static class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView recipeNameTextView;
        TextView instructionListTextView;
        TextView ingredientListTextView;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeNameTextView = itemView.findViewById(R.id.recipeName);
            instructionListTextView = itemView.findViewById(R.id.instructionList);
            ingredientListTextView = itemView.findViewById(R.id.ingredientList);
        }
       public void bind(Recipe recipe) {
           recipeNameTextView.setText(recipe.getRecipeName());

           List<String> ingredientList = recipe.getIngredients();
           StringBuilder ingredientsStringBuilder = new StringBuilder();

           for (String ingredient : ingredientList) {
               ingredientsStringBuilder.append(ingredient).append("\n");
           }

           ingredientListTextView.setText(ingredientsStringBuilder.toString());

           instructionListTextView.setText(recipe.getInstructions());
       }
    }
    public void updateRecipes(List<Recipe> recipes) {
        this.recipeList.clear();
        this.recipeList.addAll(recipes);
        notifyDataSetChanged();
    }

}