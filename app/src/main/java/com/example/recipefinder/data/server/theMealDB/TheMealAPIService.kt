package com.example.recipefinder.data.server.theMealDB

import com.example.recipefinder.data.server.getRetrofit
import retrofit2.http.GET
import retrofit2.http.Path

const val ROOT_URL_THE_MEAL = "www.themealdb.com/api/json/v1/1/"

const val MEAL_BY_NAME = "search.php?s={mealName}"
const val MEAL_BY_FIRST_LETTER = "search.php?f={letter}"
const val MEAL_BY_ID = "lookup.php?i={mealID}"
const val RANDOM_MEAL = "random.php"
const val MEAL_CATEGORIES = "categories.php"
const val LIST_OF_AREAS = "list.php?a=list"
const val LIST_OF_INGREDIENTS = "list.php?i=list"
const val FILTER_BY_INGREDIENT = "filter.php?i={ingredient}"
const val FILTER_BY_CATEGORY = "filter.php?c={category}"
const val FILTER_BY_AREA = "filter.php?a={area}"


interface TheMealAPIService {

    @GET(MEAL_BY_NAME)
    suspend fun getByName(@Path("mealName") mealName: String): MealsResponse

    @GET(MEAL_BY_FIRST_LETTER)
    suspend fun getByFirstLetter(@Path("letter") letter: String): MealsResponse

    @GET(MEAL_BY_ID)
    suspend fun getByID(@Path("mealID") mealID: String): MealsResponse

    @GET(RANDOM_MEAL)
    suspend fun getRandomMeal(): MealsResponse

    @GET(MEAL_CATEGORIES)
    suspend fun getCategories(): CategoryResponse

    @GET(LIST_OF_AREAS)
    suspend fun getListOfAreas(): AreasListResponse

    @GET(LIST_OF_INGREDIENTS)
    suspend fun getListOfIngredients(): IngredientsListResponse

    @GET(FILTER_BY_INGREDIENT)
    suspend fun filterByIngredient(@Path("ingredient") ingredient: String): GeneralMealResponse

    @GET(FILTER_BY_CATEGORY)
    suspend fun filterByCategory(@Path("category") category: String): GeneralMealResponse

    @GET(FILTER_BY_AREA)
    suspend fun filterByArea(@Path("area") area: String): GeneralMealResponse

}

object TheMealApi {
    val retrofitService: TheMealAPIService by lazy {
        getRetrofit(ROOT_URL_THE_MEAL).create(TheMealAPIService::class.java)
    }
}