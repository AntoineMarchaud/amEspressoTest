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


    // Check start of AskIdentityActivity after click on Continue button
    @Test
    fun navigationToAskIdentityActivityTest() {

        // Create a TestNavHostController
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        runOnUiThread {
            navController.setGraph(R.navigation.nav_graph)
        }

        val scenarioStart = launchFragmentInContainer {
            StartFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        Navigation.setViewNavController(fragment.requireView(), navController)
                    }
                }
            }
        }

        // Verify that performing a click changes the NavController’s state
        Espresso.onView(ViewMatchers.withId(R.id.continueButton)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.askIdentityFragment)
    }
}