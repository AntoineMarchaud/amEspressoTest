package com.amespressotest

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.amespressotest.simple.R
import com.amespressotest.simple.ui.StartFragment
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class StartFragmentTests {

    private lateinit var navController : TestNavHostController

    @Before
    fun setUpTest() {
        // Create a TestNavHostController
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        runOnUiThread {
            navController.setGraph(R.navigation.nav_graph)
        }

        val scenarioStart = launchFragmentInContainer<StartFragment>()

        // Set the NavController property on the fragment
        scenarioStart.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    // Check start of AskIdentityActivity after click on Continue button
    @Test
    fun navigationToAskIdentityActivityTest() {
        // Verify that performing a click changes the NavController’s state
        Espresso.onView(ViewMatchers.withId(R.id.continueButton)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.askIdentityFragment)
    }
}