package com.example.use

import com.example.data.repository.RecipeRepository
import com.example.domain.Category
import com.example.domain.Either
import com.example.domain.Ingredient
import com.example.domain.Recipe
import com.example.domain.RecipeName

class Recipe(private val recipeRepository: RecipeRepository) {
    suspend fun filterByArea(area: String): Either<String, List<RecipeName>> = recipeRepository.filterByArea(area)
    suspend fun filterByCategory(category: String): Either<String, List<RecipeName>> = recipeRepository.filterByCategory(category)
    suspend fun filterByIngredient(ingredient: String): Either<String, List<RecipeName>> = recipeRepository.filterByIngredient(ingredient)
    suspend fun getListOfIngredients(): Either<String, List<Ingredient>> = recipeRepository.getListOfIngredients()
    suspend fun getListOfAreas(): Either<String, List<String>> = recipeRepository.getListOfAreas()
    suspend fun getCategories(): Either<String, List<Category>> = recipeRepository.getCategories()
    suspend fun getRandomMeal(): Either<String, List<Recipe>> = recipeRepository.getRandomMeal()
    suspend fun getByID(id: String):Either<String, List<Recipe>> = recipeRepository.getRecipeById(id)
    suspend fun getByFirstLetter(letter: String):Either<String, List<Recipe>> = recipeRepository.getByFirstLetter(letter)
    suspend fun getByName(name: String):Either<String, List<Recipe>> = recipeRepository.getRecipeByName(name)
}