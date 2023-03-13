package com.example.tvshowapp

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoActivityResumedException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.tvshowapp.MainActivity.MainActivity
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    val activityScenario = ActivityScenario.launch(MainActivity::class.java)

    @Test
    fun test_IsActivityInView() {
        onView(withId(R.id.floatingActionButton)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_recyclerview_and_sortingButton() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.recycler_view_tvShowList))
            .check(matches(isDisplayed()))

        onView(withId(R.id.floatingActionButton))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun test_sortButton_restartsActivity() {
        try{
            onView(withId(R.id.floatingActionButton)).perform(click())
        }
        catch (e:Exception) {
            // assert the activity is finished
            assertTrue(activityScenario.state == Lifecycle.State.DESTROYED)
        }
    }
}