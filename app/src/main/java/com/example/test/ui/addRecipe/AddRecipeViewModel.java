package com.example.test.ui.addRecipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddRecipeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AddRecipeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Recipe Data:");
    }

    public LiveData<String> getText() {
        return mText;
    }
}