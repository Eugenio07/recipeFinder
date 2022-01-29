package com.example.recipefinder.uiTest


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.runner.AndroidJUnit4
import com.example.recipefinder.MainActivity
import com.example.recipefinder.R
import com.example.recipefinder.atPosition
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.OkHttpClient
import org.junit.Before
import javax.inject.Inject
import com.jakewharton.espresso.OkHttp3IdlingResource


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ChangeTextBehaviorTest {

    @get:Rule
    var mActivityTestRule: ActivityTestRule<MainActivity?>? =
        ActivityTestRule(MainActivity::class.java)

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        hiltRule.inject()

        val resource = OkHttp3IdlingResource.create("OkHttp", okHttpClient)

        IdlingRegistry.getInstance().register(resource)
    }

    @Test
    fun validateCountryText() {
        onView(withId(R.id.title_country))
            .check(matches(withText("Countries")))
        onView(withId(R.id.title_country)).perform(click())

        onView(withId(R.id.rv_secondary)).check(
            matches(
                atPosition(
                    3,
                    hasDescendant(withText("Canada"))
                )
            )
        )
        onView(withId(R.id.rv_secondary)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click())
        )

        Thread.sleep(1000)
        onView(withId(R.id.rv_recipes)).check(
            matches(
                atPosition(
                    1,
                    hasDescendant(withText("Breakfast Potatoes"))
                )
            )
        )
        onView(withId(R.id.rv_recipes)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )

        onView(withId(R.id.rv_ingredients))
            .check(
                matches(
                    atPosition(
                        0,
                        hasDescendant(withText("Potatoes"))
                    )
                )
            )
    }
}
