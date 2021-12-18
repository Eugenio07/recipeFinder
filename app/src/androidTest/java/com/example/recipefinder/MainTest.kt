package com.example.recipefinder

import androidx.lifecycle.Observer
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.example.data.repository.CountriesRepository
import com.example.data.repository.RecipeRepository
import com.example.data.source.LocalDataSourceTest
import com.example.data.source.RemoteDataSource
import com.example.recipefinder.di.AppModule
import com.example.recipefinder.ui.detail.DetailViewModel
import com.example.use.RecipeUseCases
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@HiltAndroidTest
//@UninstallModules(AppModule::class)
class MainTest {
    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("stringTest")
    lateinit var someString: String

    @Inject
    @Named("recipeUsesCasesProviderTest1")
    lateinit var recipeUseCases: RecipeUseCases

//    @Mock
//    private lateinit var recipeParcelable: RecipeParcelable

    @Mock
    lateinit var observer: Observer<Boolean>
    private lateinit var vm: DetailViewModel

    @Before
    fun setUp(){
        hiltRule.inject()
        val  recipeParcelable = RecipeParcelable()
        vm = DetailViewModel(recipeUseCases, recipeParcelable, Dispatchers.Unconfined)
    }

    @Test
    fun someString(){
        //assertThat(someString,containsString("e"))
        vm.isFav.observeForever(observer)
        vm.findRecipe("1")
        verify(observer).onChanged(true)
    }

    @Test
    fun whenMainActivityLaunchedNavigationHelperIsInvokedForFragment() { // 7
        assertTrue(true)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object AppModule{
        @Provides
        @Singleton
        @Named("stringTest")
        fun  provideSomeString(): String{
            return "Hola freddy"
        }
    }
}