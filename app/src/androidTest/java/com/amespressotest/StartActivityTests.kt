package com.amespressotest

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.amespressotest.simple.AskIdentityActivity
import com.amespressotest.simple.R
import com.amespressotest.simple.StartActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StartActivityTests {

    // IntentsTestRule to check start of a new Activity
    @get:Rule
    val intentsTestRule = ActivityScenarioRule(StartActivity::class.java)

    @Before
    fun setupTest() {
        Intents.init()
    }

    @After
    fun cleanUp() {
        Intents.release()
    }

    // Check start of AskIdentityActivity after click on Continue button
    @Test
    fun navigationToAskIdentityActivityTest() {
        Espresso.onView(withId(R.id.continueButton)).perform(click())
        intended(hasComponent(AskIdentityActivity::class.java.name))
    }
}