package com.example.recipefinder.data.database.db

import com.example.data.source.LocalDataSource
import com.example.domain.Category
import com.example.domain.Country
import com.example.domain.Ingredient
import com.example.domain.Recipe
import com.example.recipefinder.data.*
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: RecipeDataBase) : LocalDataSource {

    private val recipeDao = db.recipeDao()
    private val countryDao = db.countryDao()
    private val categoryDao = db.categoryDao()
    private val ingredientDao = db.ingredientDao()

    override suspend fun recipeListIsEmpty(): Boolean =
        withContext(Dispatchers.IO) { recipeDao.recipeCount() <= 0 }

    override suspend fun findByID(id: String): Recipe? =
        withContext(Dispatchers.IO) { recipeDao.findById(id)?.toRecipe() }

    override suspend fun addToFavorite(recipe: Recipe) {
        withContext(Dispatchers.IO) { recipeDao.insertRecipe(recipe.toRecipeDB()) }
    }

    override suspend fun deleteFromFavorite(recipe: Recipe) {
        withContext(Dispatchers.IO) { recipeDao.deleteRecipe(recipe.toRecipeDB()) }
    }

    override suspend fun getFavorites(): List<Recipe> =
        withContext(Dispatchers.IO) { recipeDao.getAll().map { it.toRecipe() } }


    override suspend fun countryListIsEmpty(): Boolean =
        withContext(Dispatchers.IO) { countryDao.countryCount() <= 0 }

    override suspend fun getCountryList(): List<Country> =
        withContext(Dispatchers.IO) { countryDao.getCountries().map { it.toDomainArea() } }

    override suspend fun saveCountryList(countries: List<Country>) {
        withContext(Dispatchers.IO) { countryDao.insertCountries(countries.map { it.toAreaDB() }) }
    }


    override suspend fun categoryListIsEmpty(): Boolean =
        withContext(Dispatchers.IO) { categoryDao.categoryCount() <= 0 }

    override suspend fun getCategoryList(): List<Category> =
        withContext(Dispatchers.IO) { categoryDao.getCategories().map { it.toDomainCategory() } }

    override suspend fun saveCategoryList(category: List<Category>) {
        withContext(Dispatchers.IO) { categoryDao.insertCategories(category.map { it.toCategoryDB() }) }
    }


    override suspend fun ingredientListIsEmpty(): Boolean =
        withContext(Dispatchers.IO) { ingredientDao.ingredientCount() <= 0 }

    override suspend fun getIngredientList(): List<Ingredient> =
        withContext(Dispatchers.IO) {
            ingredientDao.getIngredients().map { it.toDomainIngredient() }
        }

    override suspend fun saveIngredientList(ingredient: List<Ingredient>) {
        withContext(Dispatchers.IO) { ingredientDao.insertIngredients(ingredient.map { it.toIngredientDB() }) }
    }

}