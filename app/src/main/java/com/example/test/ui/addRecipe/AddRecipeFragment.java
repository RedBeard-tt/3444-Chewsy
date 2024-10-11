package com.example.test.ui.addRecipe;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.test.R;
import com.example.test.databinding.FragmentAddRecipeBinding;
import com.example.test.ui.recipeB.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeFragment extends Fragment {


    private FragmentAddRecipeBinding binding;
    private RecipeViewModel recipeViewModel;

    public List<Recipe> recipeBook = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddRecipeViewModel addRecipeViewModel =
                new ViewModelProvider(this).get(AddRecipeViewModel.class);

        binding = FragmentAddRecipeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.addToBook.setOnClickListener(this::addRecipe);

        /*final TextView textView = binding.RecipeName;
        addRecipeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
    */

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void addRecipe(View view) {
        String recipeName = binding.RecipeName.getText().toString().trim();
        String ingredientsString = binding.Ingredients.getText().toString().trim();
        String instructions = binding.Instructions.getText().toString().trim();
        //String imageIdString = binding.ImageID.getText().toString().trim();


        if (recipeName.isEmpty() || ingredientsString.isEmpty() || instructions.isEmpty()) {
            Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        List<String> ingredients = new ArrayList<>();
        for (String ingredient : ingredientsString.split(",")) {
            ingredients.add(ingredient.trim());
        }

       /*
        int imageID;
        try {
            imageID = Integer.parseInt(imageIdString);
        } catch (NumberFormatException e) {
            imageID = 0; // Default or error value
        }
*/
        Toast.makeText(getActivity(), "Recipe added successfully!", Toast.LENGTH_SHORT).show();
        addRecipeToBook(recipeName, ingredients, instructions);
        Toast.makeText(getActivity(), "Recipe added successfully!", Toast.LENGTH_SHORT).show();
    }

    public void addRecipeToBook(String recipeName, List<String> ingredients, String instructions) {
        Recipe newRecipe = new Recipe(recipeName, ingredients, instructions);

        RecipeViewModel recipeViewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        recipeViewModel.addRecipeBook(newRecipe); // Add the recipe to the ViewModel's list

        Toast.makeText(getActivity(), "Recipe '" + recipeName + "' added to recipe book.", Toast.LENGTH_LONG).show();


        new Handler().postDelayed(() -> {
            if (isAdded() && getView() != null) {
                NavController navController = Navigation.findNavController(getView());
                navController.navigate(R.id.nav_gallery); // Navigate back to the RecipeFragment
            }
        }, 2000);
    }
}