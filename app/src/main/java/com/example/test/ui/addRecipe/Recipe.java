package com.example.test.ui.addRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recipe {
    private String RecipeName;
    private List<String> Ingredients;
    private String Instructions;
    private int ImageID;

    public Recipe(String RecipeName, List<String> Ingredients, String Instructions) {
        this.RecipeName = RecipeName;
        this.Ingredients = Ingredients;
        this.Instructions = Instructions;
  //      this.ImageID =ImageID;
}

    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String RecipeName) {
        this.RecipeName = RecipeName;
    }

    public List<String> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(List<String> Ingredients) {
        this.Ingredients = new ArrayList<>(Ingredients);;
    }

    public String getInstructions() {
        return Instructions;
    }

    public void setInstructions(String Instructions) {
        this.Instructions = Instructions;
    }

}
