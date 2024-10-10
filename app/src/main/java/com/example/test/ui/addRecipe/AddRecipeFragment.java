package com.example.test.ui.addRecipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.databinding.FragmentAddRecipeBinding;

public class AddRecipeFragment extends Fragment {

    private FragmentAddRecipeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       AddRecipeViewModel addRecipeViewModel =
                new ViewModelProvider(this).get(AddRecipeViewModel.class);

        binding = FragmentAddRecipeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAddRecipe;
        addRecipeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}