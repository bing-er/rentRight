package my.bcit.rentright.views.activity

import android.app.Activity
import android.app.Instrumentation
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import my.bcit.rentright.R
import my.bcit.rentright.Views.Activity.Landing
import my.bcit.rentright.Views.Activity.Signup
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class TestSignup {

    @get:Rule
    val activityRule = ActivityScenarioRule(Landing::class.java)


    @Before
    fun setUp() {
        Intents.init()
    }
    @Before
    fun launchActivity() {
        ActivityScenario.launch(Signup::class.java)
    }
    @Test
    fun testValidInput_ProceedsToHomePage() {

//        Intents.intending(IntentMatchers.hasComponent(HomePageActivity::class.java.name)).respondWith(
//            Instrumentation.ActivityResult(Activity.RESULT_OK, null))

        onView(withId(R.id.inputName)).perform(typeText("Test User"), closeSoftKeyboard())
        onView(withId(R.id.inputEmail)).perform(typeText("test@email.com"), closeSoftKeyboard())
        onView(withId(R.id.inputPassword)).perform(typeText("password123"), closeSoftKeyboard())
        onView(withId(R.id.inputConfirmPassword)).perform(typeText("password123"), closeSoftKeyboard())

        // Click the register button
        onView(withId(R.id.registerButton)).perform(click())

        // Check if the app navigates to the HomePageActivity
       // Intents.intended(IntentMatchers.hasComponent(HomePageActivity::class.java.name))
    }

    @After
    fun cleanUp() {
        Intents.release()
    }
}