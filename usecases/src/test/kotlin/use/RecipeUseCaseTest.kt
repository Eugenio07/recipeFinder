package use

import com.example.data.repository.RecipeRepository
import com.example.domain.Either
import com.example.use.RecipeUseCases
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import mokedDomain.mockedCategory
import mokedDomain.mockedCountry
import mokedDomain.mockedIngredient
import mokedDomain.mockedRecipe
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecipeUseCaseTest {
    @Mock
    private lateinit var recipeRepository: RecipeRepository
    private lateinit var recipeUseCase: RecipeUseCases

    @Before
    fun setUp() {
        recipeUseCase = RecipeUseCases(recipeRepository)
    }

    @Test
    fun `get random meal`(){
       runBlocking {
           val recipes = Either.Right(listOf(mockedRecipe))
           whenever(recipeRepository.getRandomMeal()).thenReturn(recipes)

           val result = recipeUseCase.getRandomMeal()

           assertEquals(recipes, result)
       }
    }

    @Test
    fun `filter by area`(){
        runBlocking {
            val recipes = Either.Right(listOf(mockedRecipe))
            whenever(recipeRepository.filterByArea("American")).thenReturn(recipes)

            val result = recipeUseCase.filterByArea("American")

            assertEquals(recipes, result)
        }
    }

    @Test
    fun `get list of ingredients`(){
        runBlocking {
            val ingredients = Either.Right(listOf(mockedIngredient))
            whenever(recipeRepository.getListOfIngredients()).thenReturn(ingredients)

            val result = recipeUseCase.getListOfIngredients()

            assertEquals(ingredients, result)
        }
    }

    @Test
    fun `get list of areas`(){
        runBlocking {
            val areas = Either.Right(listOf(mockedCountry))
            whenever(recipeRepository.getListOfAreas()).thenReturn(areas)

            val result = recipeUseCase.getListOfAreas()

            assertEquals(areas, result)
        }
    }

    @Test
    fun `get list of categories`(){
        runBlocking {
            val categories = Either.Right(listOf(mockedCategory))
            whenever(recipeRepository.getListOfCategories()).thenReturn(categories)

            val result = recipeUseCase.getListOfCategories()

            assertEquals(categories, result)
        }
    }

    @Test
    fun `save favorite recipe`(){
        runBlocking {
            val recipe = mockedRecipe

            recipeUseCase.saveFavoriteRecipe(recipe)

            verify(recipeRepository).saveFavoriteRecipe(recipe)
        }
    }

    @Test
    fun `delete favorite recipe`(){
        runBlocking {
            val recipe = mockedRecipe

            recipeUseCase.deleteFavoriteRecipe(recipe)

            verify(recipeRepository).deleteFavoriteRecipe(recipe)
        }
    }

    @Test
    fun `get favorites recipes`(){
        runBlocking {
            val recipes = listOf(mockedRecipe)
            whenever(recipeRepository.getFavoritesRecipes()).thenReturn(recipes)

            val result = recipeUseCase.getFavoritesRecipes()

            assertEquals(recipes, result)
        }
    }

    @Test
    fun `find recipe in favorites`(){
        runBlocking {
            val recipe = mockedRecipe.copy(idMeal = "1")
            whenever(recipeRepository.findFavoriteByID("1")).thenReturn(recipe)

            val result = recipeUseCase.findRecipeByID("1")

            assertEquals(recipe, result)
        }
    }

    @Test
    fun `don't find recipe in favorites`(){
        runBlocking {
            whenever(recipeRepository.findFavoriteByID("1")).thenReturn(null)

            val result = recipeUseCase.findRecipeByID("1")

            assertNull(result)
        }
    }

}