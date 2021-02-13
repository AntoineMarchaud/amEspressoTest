package com.amespressotest

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.amespressotest.simple.R
import com.amespressotest.simple.ui.AskIdentityFragment
import com.google.common.truth.Truth.assertThat
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AskIdentityFragmentTests {

    companion object {
        private const val NAME = "Antoine"
    }

    private lateinit var navController: TestNavHostController

    @Before
    fun setUpTest() {

        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.nav_graph)
        }

        val scenarioAskIdentity = launchFragmentInContainer {
            AskIdentityFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        Navigation.setViewNavController(fragment.requireView(), navController)
                    }
                }
            }
        }
    }

    // Check if name editText is empty and button is not enabled at start
    @Test
    fun emptyNameAtStartTest() {
        Espresso.onView(ViewMatchers.withId(R.id.enterNameEditText))
            .check(matches(ViewMatchers.withText("")))
        checkContinueButtonEnabled(false)
    }

    // Check if button is not enabled if name editText is empty
    @Test
    fun emptyNameTest() {
        fillName("")
        checkContinueButtonEnabled(false)
    }

    // Check if button is not enabled if name editText contains only spaces
    @Test
    fun spacesNameTest() {
        fillName(" ")
        checkContinueButtonEnabled(false)
    }

    // Check if button is enabled if name editText contains correct name
    @Test
    fun correctNameTest() {
        fillName(NAME)
        checkContinueButtonEnabled(true)
    }

    // Check if button is enabled if name editText contains correct name after containing only spaces
    @Test
    fun correctNameAfterSpacesNameTest() {
        fillName(" ")
        fillName(NAME)
        checkContinueButtonEnabled(true)
    }

    // Check if button is not enabled if name editText is empty after containing correct name
    @Test
    fun emptyNameAfterCorrectNameTest() {
        fillName(NAME)
        fillName("")
        checkContinueButtonEnabled(false)
    }

    // Check if button is not enabled if name editText contains only spaces after containing correct name
    @Test
    fun spacesNameAfterCorrectNameTest() {
        fillName(NAME)
        fillName(" ")
        checkContinueButtonEnabled(false)
    }

    // Check start of EndActivity after click on Continue button
    @Test
    fun navigationToEndActivityTest() {
        fillName(NAME)
        Espresso.onView(ViewMatchers.withId(R.id.endButton)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.endFragment)
    }

    // Fill Name edittext with text
    private fun fillName(text: String) {
        Espresso.onView(ViewMatchers.withId(R.id.enterNameEditText))
            .perform(
                ViewActions.clearText(),
                ViewActions.typeText(text),
                ViewActions.pressImeActionButton()
            )
    }

    // Check if continue Button is enabled or not
    private fun checkContinueButtonEnabled(enabled: Boolean) {
        Espresso.onView(ViewMatchers.withId(R.id.endButton))
            .check(matches(if (enabled) isEnabled() else not(isEnabled())))
    }
}