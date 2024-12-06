package com.example.chewsyui.ui.addRecipe

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val recipeName: String,
    val ingredients: List<String>,
    val instructions: String
) : Parcelable
