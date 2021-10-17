package com.example.recipefinder.data

import com.example.domain.Category
import com.example.domain.Country
import com.example.domain.Ingredient
import com.example.domain.Recipe
import com.example.recipefinder.RecipeApp
import com.example.recipefinder.data.database.entity.CategoryDB
import com.example.recipefinder.data.database.entity.CountryDB
import com.example.recipefinder.data.database.entity.IngredientDB
import com.example.recipefinder.data.database.entity.RecipeDB
import com.example.recipefinder.data.server.restCountries.RestCountryResponse
import com.example.recipefinder.data.server.theMealDB.*

fun Recipe.toRecipeDB(): RecipeDB =
    RecipeDB(
        idMeal!!,
        dateModified,
        strArea,
        strCategory,
        strCreativeCommonsConfirmed,
        strDrinkAlternate,
        strImageSource,
        strIngredient1,
        strIngredient2,
        strIngredient3,
        strIngredient4,
        strIngredient5,
        strIngredient6,
        strIngredient7,
        strIngredient8,
        strIngredient9,
        strIngredient10,
        strIngredient11,
        strIngredient12,
        strIngredient13,
        strIngredient14,
        strIngredient15,
        strIngredient16,
        strIngredient17,
        strIngredient18,
        strIngredient19,
        strIngredient20,
        strInstructions,
        strMeal,
        strMealThumb,
        strMeasure1,
        strMeasure2,
        strMeasure3,
        strMeasure4,
        strMeasure5,
        strMeasure6,
        strMeasure7,
        strMeasure8,
        strMeasure9,
        strMeasure10,
        strMeasure11,
        strMeasure12,
        strMeasure13,
        strMeasure14,
        strMeasure15,
        strMeasure16,
        strMeasure17,
        strMeasure18,
        strMeasure19,
        strMeasure20,
        strSource,
        strTags,
        strYoutube
    )

fun RecipeDB.toRecipe(): Recipe =
    Recipe(
        idMeal,
        dateModified,
        strArea,
        strCategory,
        strCreativeCommonsConfirmed,
        strDrinkAlternate,
        strImageSource,
        strIngredient1,
        strIngredient2,
        strIngredient3,
        strIngredient4,
        strIngredient5,
        strIngredient6,
        strIngredient7,
        strIngredient8,
        strIngredient9,
        strIngredient10,
        strIngredient11,
        strIngredient12,
        strIngredient13,
        strIngredient14,
        strIngredient15,
        strIngredient16,
        strIngredient17,
        strIngredient18,
        strIngredient19,
        strIngredient20,
        strInstructions,
        strMeal,
        strMealThumb,
        strMeasure1,
        strMeasure2,
        strMeasure3,
        strMeasure4,
        strMeasure5,
        strMeasure6,
        strMeasure7,
        strMeasure8,
        strMeasure9,
        strMeasure10,
        strMeasure11,
        strMeasure12,
        strMeasure13,
        strMeasure14,
        strMeasure15,
        strMeasure16,
        strMeasure17,
        strMeasure18,
        strMeasure19,
        strMeasure20,
        strSource,
        strTags,
        strYoutube
    )

fun MealsResponse.Meal.toRecipe(): Recipe = Recipe(
    idMeal,
    dateModified,
    strArea,
    strCategory,
    strCreativeCommonsConfirmed,
    strDrinkAlternate,
    strImageSource,
    strIngredient1,
    strIngredient2,
    strIngredient3,
    strIngredient4,
    strIngredient5,
    strIngredient6,
    strIngredient7,
    strIngredient8,
    strIngredient9,
    strIngredient10,
    strIngredient11,
    strIngredient12,
    strIngredient13,
    strIngredient14,
    strIngredient15,
    strIngredient16,
    strIngredient17,
    strIngredient18,
    strIngredient19,
    strIngredient20,
    strInstructions,
    strMeal,
    strMealThumb,
    strMeasure1,
    strMeasure2,
    strMeasure3,
    strMeasure4,
    strMeasure5,
    strMeasure6,
    strMeasure7,
    strMeasure8,
    strMeasure9,
    strMeasure10,
    strMeasure11,
    strMeasure12,
    strMeasure13,
    strMeasure14,
    strMeasure15,
    strMeasure16,
    strMeasure17,
    strMeasure18,
    strMeasure19,
    strMeasure20,
    strSource,
    strTags,
    strYoutube
)

fun Recipe.toRecipeApp(): RecipeApp =
    RecipeApp(
        idMeal,
        dateModified,
        strArea,
        strCategory,
        strCreativeCommonsConfirmed,
        strDrinkAlternate,
        strImageSource,
        strIngredient1,
        strIngredient2,
        strIngredient3,
        strIngredient4,
        strIngredient5,
        strIngredient6,
        strIngredient7,
        strIngredient8,
        strIngredient9,
        strIngredient10,
        strIngredient11,
        strIngredient12,
        strIngredient13,
        strIngredient14,
        strIngredient15,
        strIngredient16,
        strIngredient17,
        strIngredient18,
        strIngredient19,
        strIngredient20,
        strInstructions,
        strMeal,
        strMealThumb,
        strMeasure1,
        strMeasure2,
        strMeasure3,
        strMeasure4,
        strMeasure5,
        strMeasure6,
        strMeasure7,
        strMeasure8,
        strMeasure9,
        strMeasure10,
        strMeasure11,
        strMeasure12,
        strMeasure13,
        strMeasure14,
        strMeasure15,
        strMeasure16,
        strMeasure17,
        strMeasure18,
        strMeasure19,
        strMeasure20,
        strSource,
        strTags,
        strYoutube
    )

fun RecipeApp.toRecipe(): Recipe =
    Recipe(
        idMeal,
        dateModified,
        strArea,
        strCategory,
        strCreativeCommonsConfirmed,
        strDrinkAlternate,
        strImageSource,
        strIngredient1,
        strIngredient2,
        strIngredient3,
        strIngredient4,
        strIngredient5,
        strIngredient6,
        strIngredient7,
        strIngredient8,
        strIngredient9,
        strIngredient10,
        strIngredient11,
        strIngredient12,
        strIngredient13,
        strIngredient14,
        strIngredient15,
        strIngredient16,
        strIngredient17,
        strIngredient18,
        strIngredient19,
        strIngredient20,
        strInstructions,
        strMeal,
        strMealThumb,
        strMeasure1,
        strMeasure2,
        strMeasure3,
        strMeasure4,
        strMeasure5,
        strMeasure6,
        strMeasure7,
        strMeasure8,
        strMeasure9,
        strMeasure10,
        strMeasure11,
        strMeasure12,
        strMeasure13,
        strMeasure14,
        strMeasure15,
        strMeasure16,
        strMeasure17,
        strMeasure18,
        strMeasure19,
        strMeasure20,
        strSource,
        strTags,
        strYoutube
    )

fun AreasListResponse.Meal.toCountryString(): String = strArea!!

fun CountryDB.toDomainCountry(): Country =
    Country(name, demonym, flag, recipeCountry)

fun Country.toAreaDB(): CountryDB =
    CountryDB(strArea!!, demonym, flag, recipeCountry)

fun CategoryResponse.Category.toDomainCategory(): Category =
    Category(idCategory, strCategory, strCategoryThumb, strCategoryDescription)

fun CategoryDB.toDomainCategory(): Category =
    Category(idCategory, strCategory, strCategoryThumb, strCategoryDescription)

fun Category.toCategoryDB(): CategoryDB =
    CategoryDB(idCategory!!, strCategory, strCategoryThumb, strCategoryDescription)

fun IngredientsListResponse.Meal.toDomainIngredient(): Ingredient =
    Ingredient(idIngredient, strIngredient, strDescription, strType)

fun IngredientDB.toDomainIngredient(): Ingredient =
    Ingredient(idIngredient, strIngredient, strDescription, strType)

fun Ingredient.toIngredientDB(): IngredientDB =
    IngredientDB(idIngredient!!, strIngredient, strDescription, strType)

fun GeneralMealResponse.Meal.toFilterRecipe(): Recipe =
    Recipe(strMeal = strMeal, strMealThumb = strMealThumb, idMeal = idMeal)

fun RestCountryResponse.toDomainCountry(): Country =
    Country(name, demonym, flags?.png)