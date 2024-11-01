package com.example.test.ui.recipeB;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.databinding.FragmentGalleryBinding; // Ensure this is the correct binding file for your layout
import com.example.test.ui.addRecipe.Recipe;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class RecipeFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private RecipeAdapter recipeAdapter; // Define recipeAdapter here
    private List<Recipe> recipeBook = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RecipeViewModel recipeViewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);

        // Inflate the binding for the layout
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Set up RecyclerView
        RecyclerView recyclerView = binding.recipeRecycler; // Ensure this ID is correct in your layout
        recipeAdapter = new RecipeAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // Use getContext() instead of getActivity()
        recyclerView.setAdapter(recipeAdapter);

        // Observe the recipe list from ViewModel

        recipeViewModel.getRecipeBook().observe(getViewLifecycleOwner(), recipes -> {
            recipeAdapter.updateRecipes(recipes); // Update the adapter's list
        });

        /*  recipeViewModel.getRecipeBook().observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                recipeAdapter.updateRecipes(recipes); // Ensure this method exists in your adapter
            }
        }); */

        // Handle Add Recipe button click
        binding.addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add recipe to Recipe Book", Snackbar.LENGTH_LONG)
                        .setAction("Add Recipe", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                NavController navController = Navigation.findNavController(view);
                                navController.navigate(R.id.nav_addRecipe);
                            }
                        }).show();
            }
        });


        final TextView textView = binding.textGallery;

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Prevent memory leaks
    }
}