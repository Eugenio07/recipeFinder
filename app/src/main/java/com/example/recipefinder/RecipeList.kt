package com.example.recipefinder

import android.os.Parcelable
import com.example.domain.Recipe
import kotlinx.android.parcel.Parcelize

@Parcelize
class RecipeList : ArrayList<Recipe>(), Parcelable


