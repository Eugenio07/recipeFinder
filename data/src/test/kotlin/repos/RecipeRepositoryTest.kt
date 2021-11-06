package repos

import com.example.data.repository.CountriesRepository
import com.example.data.repository.RecipeRepository
import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.Either
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyBlocking
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import mockedDomain.mockedCategory
import mockedDomain.mockedCountry
import mockedDomain.mockedIngredient
import mockedDomain.mockedRecipe
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecipeRepositoryTest {
    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    @Mock
    lateinit var countryRepository: CountriesRepository

    private lateinit var recipeRepository: RecipeRepository

    @Before
    fun setUp() {
        recipeRepository =
            RecipeRepository(localDataSource, remoteDataSource, countryRepository)
    }

    @Test
    fun `get favorites recipes`() {
        runBlocking {
            val recipes = listOf(mockedRecipe)

            whenever(localDataSource.getFavorites()).thenReturn(recipes)

            val result = recipeRepository.getFavoritesRecipes()

            Assert.assertEquals(recipes, result)
        }
    }

    @Test
    fun `save favorite recipes`() {
        runBlocking {
            val recipe = mockedRecipe

            recipeRepository.saveFavoriteRecipe(recipe)

            verify(localDataSource).addToFavorite(recipe)
        }
    }

    @Test
    fun `delete favorite recipes`() {
        runBlocking {
            val recipe = mockedRecipe

            recipeRepository.deleteFavoriteRecipe(recipe)

            verify(localDataSource).deleteFromFavorite(recipe)
        }
    }

    @Test
    fun `find favorite recipe by ID`() {
        runBlocking {
            val recipe = mockedRecipe.copy(idMeal = "1")

            whenever(localDataSource.findByID("1")).thenReturn(recipe)

            val result = recipeRepository.findFavoriteByID("1")

            Assert.assertEquals(recipe, result)
        }
    }

    @Test
    fun `get recipe by ID`() {
        runBlocking {
            val recipe = Either.Right(listOf(mockedRecipe.copy(idMeal = "1")))

            whenever(remoteDataSource.getByID("1")).thenReturn(recipe)

            val result = recipeRepository.getRecipeById("1") as Either.Right

            Assert.assertEquals(recipe, result)
        }
    }

    @Test
    fun `get recipe by name`() {
        runBlocking {
            val recipe = Either.Right(listOf(mockedRecipe.copy(strMeal = "Arepa")))

            whenever(remoteDataSource.getByName("Arepa")).thenReturn(recipe)

            val result = recipeRepository.getRecipeByName("Arepa") as Either.Right

            Assert.assertEquals(recipe, result)
        }
    }

    @Test
    fun `get random recipe`() {
        runBlocking {
            val recipe = Either.Right(listOf(mockedRecipe.copy(strMeal = "Arepa")))

            whenever(remoteDataSource.getRandomMeal()).thenReturn(recipe)

            val result = recipeRepository.getRandomMeal() as Either.Right

            Assert.assertEquals(recipe, result)
        }
    }

    /*@Test
    fun `get list of areas`() {
        runBlocking {
            val countries = listOf(mockedCountry)
            val areas = listOf("strArea")
            val demonyms = listOf("demonym")
            whenever(localDataSource.recipeCountryListIsEmpty()).thenReturn(true)
            whenever(remoteDataSource.getListOfAreas()).thenReturn(Either.Right(areas))
            whenever(localDataSource.getCountryListByDemonym(demonyms)).thenReturn(countries)
            //countries.forEach { it.recipeCountry = true }
            whenever(localDataSource.getRecipeCountryList()).thenReturn(countries)

            val result = recipeRepository.getListOfAreas() as Either.Right

            verify(localDataSource).updateCountryList(countries)
            Assert.assertEquals(countries, result.r)
        }
    }*/

    @Test
    fun `get list of ingredients from remote`() {
        runBlocking {
            val ingredients = Either.Right(listOf(mockedIngredient))

            whenever(localDataSource.ingredientListIsEmpty()).thenReturn(true)
            whenever(remoteDataSource.getListOfIngredients()).thenReturn(ingredients)
            whenever(localDataSource.getIngredientList()).thenReturn(ingredients.r)

            val result = recipeRepository.getListOfIngredients() as Either.Right
            verify(localDataSource).saveIngredientList(ingredients.r)
            verify(localDataSource).getIngredientList()
            Assert.assertEquals(ingredients, result)

        }
    }

    @Test
    fun `get list of ingredients from BD`() {
        runBlocking {
            val ingredients = Either.Right(listOf(mockedIngredient))

            whenever(localDataSource.ingredientListIsEmpty()).thenReturn(false)
            whenever(localDataSource.getIngredientList()).thenReturn(ingredients.r)

            val result = recipeRepository.getListOfIngredients() as Either.Right
            verify(localDataSource).getIngredientList()
            Assert.assertEquals(ingredients, result)
        }
    }

    @Test
    fun `get list of categories from remote`() {
        runBlocking {
            val categories = Either.Right(listOf(mockedCategory))

            whenever(localDataSource.categoryListIsEmpty()).thenReturn(true)
            whenever(remoteDataSource.getListOfCategories()).thenReturn(categories)
            whenever(localDataSource.getCategoryList()).thenReturn(categories.r)

            val result = recipeRepository.getListOfCategories() as Either.Right
            verify(localDataSource).saveCategoryList(categories.r)
            verify(localDataSource).getCategoryList()
            Assert.assertEquals(categories, result)

        }
    }

    @Test
    fun `get list of categories from BD`() {
        runBlocking {
            val categories = Either.Right(listOf(mockedCategory))

            whenever(localDataSource.categoryListIsEmpty()).thenReturn(true)
            whenever(localDataSource.getCategoryList()).thenReturn(categories.r)

            val result = recipeRepository.getListOfCategories() as Either.Right
            verify(localDataSource).getCategoryList()
            Assert.assertEquals(categories, result)
        }
    }

    @Test
    fun `filter by ingredient`() {
        runBlocking {
            val recipes = Either.Right(listOf(mockedRecipe))

            whenever(remoteDataSource.filterByIngredient("meat")).thenReturn(recipes)

            val result = recipeRepository.filterByIngredient("meat")
            Assert.assertEquals(recipes, result)
        }
    }

    @Test
    fun `filter by category`() {
        runBlocking {
            val recipes = Either.Right(listOf(mockedRecipe))

            whenever(remoteDataSource.filterByCategory("seafood")).thenReturn(recipes)

            val result = recipeRepository.filterByCategory("seafood")
            Assert.assertEquals(recipes, result)
        }
    }

    @Test
    fun `filter by area`() {
        runBlocking {
            val recipes = Either.Right(listOf(mockedRecipe))

            whenever(remoteDataSource.filterByArea("Spain")).thenReturn(recipes)

            val result = recipeRepository.filterByArea("Spain")
            Assert.assertEquals(recipes, result)
        }
    }

    @Test
    fun `filter by my area`() {
        runBlocking {
            val recipes = Either.Right(listOf(mockedRecipe))
            whenever(countryRepository.getLocation()).thenReturn("Spain")

            whenever(remoteDataSource.filterByArea("Spain")).thenReturn(recipes)
            val result = recipeRepository.filterByMyArea()
            Assert.assertEquals(recipes, result)
        }
    }


}