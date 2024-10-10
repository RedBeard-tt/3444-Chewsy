package com.example.test;
import java.util.LinkedList;

public class RecipeBook extends Recipe {

    public static LinkedList<Recipe> Book = new LinkedList<Recipe>();

    public boolean addRecipe(Recipe r){
        if(Book.add(r)){
            System.out.println("hello");
            return true;
        };
        return false;
    }



}
