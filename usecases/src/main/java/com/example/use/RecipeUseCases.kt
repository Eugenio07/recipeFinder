package com.example.use

import com.example.data.repository.RecipeRepository
import com.example.domain.*

class RecipeUseCases(private val recipeRepository: RecipeRepository) {
    suspend fun filterByArea(area: String): Either<String, List<Recipe>> = recipeRepository.filterByArea(area)
    suspend fun filterByCategory(category: String): Either<String, List<Recipe>> = recipeRepository.filterByCategory(category)
    suspend fun filterByIngredient(ingredient: String): Either<String, List<Recipe>> = recipeRepository.filterByIngredient(ingredient)

    suspend fun getListOfIngredients(): Either<String, List<Ingredient>> = recipeRepository.getListOfIngredients()
    suspend fun getListOfAreas(): Either<String, List<Country>> = recipeRepository.getListOfAreas()
    suspend fun getListOfCategories(): Either<String, List<Category>> = recipeRepository.getListOfCategories()

    suspend fun getRandomMeal(): Either<String, List<Recipe>> = recipeRepository.getRandomMeal()
    suspend fun getByID(id: String):Either<String, List<Recipe>> = recipeRepository.getRecipeById(id)
    suspend fun getByFirstLetter(letter: String):Either<String, List<Recipe>> = recipeRepository.getByFirstLetter(letter)
    suspend fun getByName(name: String):Either<String, List<Recipe>> = recipeRepository.getRecipeByName(name)

    suspend fun deleteFavoriteRecipe(recipe: Recipe) = recipeRepository.deleteFavoriteRecipe(recipe)
    suspend fun saveFavoriteRecipe(recipe: Recipe) = recipeRepository.saveFavoriteRecipe(recipe)
    suspend fun getFavoritesRecipes() = recipeRepository.getFavoritesRecipes()
    suspend fun findRecipeByID(id:String) : Recipe? = recipeRepository.findFavoriteByID(id)

}