package com.amespressotest

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.amespressotest.simple.AskIdentityActivity
import com.amespressotest.simple.EndActivity
import com.amespressotest.simple.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EndActivityTests {

    companion object {
        // Name Constant
        private const val NAME = "Antoine"
    }

    // IntentsTestRule to check start of a new Activity
    @get:Rule
    val intentsTestRule = ActivityScenarioRule(AskIdentityActivity::class.java)

    @Before
    fun setupTest() {
        Intents.init()
    }

    @After
    fun cleanUp() {
        Intents.release()
    }

    // Check if name from previous activity is displayed : hello [name]!
    @Test
    fun nameDisplayedTest() {
        Espresso.onView(ViewMatchers.withId(R.id.enterNameEditText)).perform(typeText(NAME), pressImeActionButton())
        //Espresso.onView(ViewMatchers.withId(R.id.continueButton)).check(matches(isEnabled()))
        Espresso.onView(ViewMatchers.withId(R.id.continueButton)).perform(click())
        Intents.intended(IntentMatchers.hasComponent(EndActivity::class.java.name))
        Espresso.onView(ViewMatchers.withId(R.id.helloName)).check(matches(withText("Hello $NAME!")))
    }
}