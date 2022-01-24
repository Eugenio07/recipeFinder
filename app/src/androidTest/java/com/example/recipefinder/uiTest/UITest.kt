package com.example.recipefinder.uiTest


import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.runner.AndroidJUnit4
import com.example.recipefinder.MainActivity
import com.example.recipefinder.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ChangeTextBehaviorTest {

    @get:Rule
    var mActivityTestRule: ActivityTestRule<MainActivity?>? =
        ActivityTestRule(MainActivity::class.java)
    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun validateEditText() {
        Espresso.onView(ViewMatchers.withId(R.id.title_country))
            .check(ViewAssertions.matches(ViewMatchers.withText("Countries")))
    }
}
