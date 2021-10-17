package com.example.recipefinder.data.server.theMealDB

import com.example.recipefinder.data.server.getRetrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val ROOT_URL_THE_MEAL = "https://themealdb.com/api/json/v1/1/"

//const val MEAL_BY_NAME = "search.php?s={mealName}"
//const val MEAL_BY_FIRST_LETTER = "search.php?f={letter}"
//const val MEAL_BY_ID = "lookup.php?i={mealID}"
const val MEAL_BY_NAME = "search.php"
const val MEAL_BY_FIRST_LETTER = "search.php"
const val MEAL_BY_ID = "lookup.php"
const val RANDOM_MEAL = "random.php"
const val MEAL_CATEGORIES = "categories.php"
const val LIST_OF_AREAS = "list.php?a=list"
const val LIST_OF_INGREDIENTS = "list.php?i=list"
//const val FILTER_BY_INGREDIENT = "filter.php?i={ingredient}"
//const val FILTER_BY_CATEGORY = "filter.php?c={category}"
//const val FILTER_BY_AREA = "filter.php?a={area}"
const val FILTER_BY_INGREDIENT = "filter.php"
const val FILTER_BY_CATEGORY = "filter.php"
const val FILTER_BY_AREA = "filter.php"


interface TheMealAPIService {

    @GET(MEAL_BY_NAME)
    suspend fun getByName(@Query("s") mealName: String): MealsResponse

    @GET(MEAL_BY_FIRST_LETTER)
    suspend fun getByFirstLetter(@Query("f") letter: String): MealsResponse

    @GET(MEAL_BY_ID)
    suspend fun getByID(@Query("i") mealID: String): MealsResponse

    @GET(RANDOM_MEAL)
    suspend fun getRandomMeal(): MealsResponse

    @GET(MEAL_CATEGORIES)
    suspend fun getListOfCategories(): CategoryResponse

    @GET(LIST_OF_AREAS)
    suspend fun getListOfAreas(): AreasListResponse

    @GET(LIST_OF_INGREDIENTS)
    suspend fun getListOfIngredients(): IngredientsListResponse

    @GET(FILTER_BY_INGREDIENT)
    suspend fun filterByIngredient(@Query("i") ingredient: String): GeneralMealResponse

    @GET(FILTER_BY_CATEGORY)
    suspend fun filterByCategory(@Query("c") category: String): GeneralMealResponse

    @GET(FILTER_BY_AREA)
    suspend fun filterByArea(@Query("a") area: String): GeneralMealResponse

}

object TheMealApi {
    val retrofitService: TheMealAPIService by lazy {
        getRetrofit(ROOT_URL_THE_MEAL).create(TheMealAPIService::class.java)
    }
}


enum class NETWORK_STATUS  {DONE,LOADING,ERROR}