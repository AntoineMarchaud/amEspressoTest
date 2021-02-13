package com.amespressotest

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.amespressotest.simple.R
import com.amespressotest.simple.ui.AskIdentityFragment
import com.amespressotest.simple.ui.EndFragment
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EndFragmentTests {

    companion object {
        // Name Constant
        private const val NAME = "Antoine"
    }

    private lateinit var navController: TestNavHostController

    @Before
    fun setUpTest() {
        // Create a TestNavHostController
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.nav_graph)
        }

        val bundle = bundleOf("name" to NAME)
        val scenario = launchFragmentInContainer<EndFragment>(bundle)

        // Set the NavController property on the fragment
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    // Check if name from previous activity is displayed : hello [name]!
    @Test
    fun nameDisplayedTest() {
        Espresso.onView(ViewMatchers.withId(R.id.helloName))
            .check(matches(withText("Hello $NAME!")))
    }
}