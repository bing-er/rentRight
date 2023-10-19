package my.bcit.rentright.views.activity

import android.app.Activity
import android.app.Instrumentation
import android.view.View
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
import com.google.android.material.textfield.TextInputLayout
import my.bcit.rentright.R
import my.bcit.rentright.Views.Activity.HomePageActivity
import my.bcit.rentright.Views.Activity.Landing
import my.bcit.rentright.Views.Activity.Login
import my.bcit.rentright.Views.Activity.Signup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
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

      Intents.intending(IntentMatchers.hasComponent(HomePageActivity::class.java.name)).respondWith(
          Instrumentation.ActivityResult(Activity.RESULT_OK, null))

        onView(withId(R.id.inputName)).perform(typeText("Test User"), closeSoftKeyboard())
        onView(withId(R.id.inputEmail)).perform(typeText("test@email.com"), closeSoftKeyboard())
        onView(withId(R.id.inputPassword)).perform(typeText("password123"), closeSoftKeyboard())
        onView(withId(R.id.inputConfirmPassword)).perform(typeText("password123"), closeSoftKeyboard())

        onView(withId(R.id.registerButton)).perform(click())

        // Check if the app navigates to the HomePageActivity
       Intents.intended(IntentMatchers.hasComponent(HomePageActivity::class.java.name))
    }

    @Test
    fun testFieldsForEmptyInput() {
        // Click the register button without entering any data
        onView(withId(R.id.registerButton)).perform(click())

        // Check if the error is displayed for each field
        onView(withId(R.id.displayName)).check(matches(hasError()))
        onView(withId(R.id.displayEmail)).check(matches(hasError()))
        onView(withId(R.id.displayPassword)).check(matches(hasError()))
    }

    @Test
    fun testInvalidEmail() {
        onView(withId(R.id.inputName)).perform(typeText("Test User"), closeSoftKeyboard())
        onView(withId(R.id.inputEmail)).perform(typeText("testemail.com"), closeSoftKeyboard())
        onView(withId(R.id.inputPassword)).perform(typeText("password123"), closeSoftKeyboard())
        onView(withId(R.id.inputConfirmPassword)).perform(typeText("password123"), closeSoftKeyboard())

        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.displayEmail)).check(matches(hasError()))
    }

    @Test
    fun testUnMatchPassword() {
        onView(withId(R.id.inputName)).perform(typeText("Test User"), closeSoftKeyboard())
        onView(withId(R.id.inputEmail)).perform(typeText("test@email.com"), closeSoftKeyboard())
        onView(withId(R.id.inputPassword)).perform(typeText("password123"), closeSoftKeyboard())
        onView(withId(R.id.inputConfirmPassword)).perform(typeText("password156"), closeSoftKeyboard())

        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.displayConfirmPassword)).check(matches(hasError()))

    }

    @Test
    fun testNameForEmptyInput() {
        onView(withId(R.id.inputEmail)).perform(typeText("test@email.com"), closeSoftKeyboard())
        onView(withId(R.id.inputPassword)).perform(typeText("password123"), closeSoftKeyboard())
        onView(withId(R.id.inputConfirmPassword)).perform(typeText("password123"), closeSoftKeyboard())

        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.displayName)).check(matches(hasError()))

    }

    @Test
    fun testEmailForEmptyInput() {
        onView(withId(R.id.inputName)).perform(typeText("Test User"), closeSoftKeyboard())
        onView(withId(R.id.inputPassword)).perform(typeText("password123"), closeSoftKeyboard())
        onView(withId(R.id.inputConfirmPassword)).perform(typeText("password123"), closeSoftKeyboard())

        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.displayEmail)).check(matches(hasError()))
    }

    @Test
    fun testPasswordForEmptyInput() {
        onView(withId(R.id.inputName)).perform(typeText("Test User"), closeSoftKeyboard())
        onView(withId(R.id.inputEmail)).perform(typeText("test@email.com"), closeSoftKeyboard())
        onView(withId(R.id.inputConfirmPassword)).perform(typeText("password123"), closeSoftKeyboard())

        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.displayPassword)).check(matches(hasError()))
    }

    @Test
    fun testConfirmPasswordForEmptyInput() {
        onView(withId(R.id.inputName)).perform(typeText("Test User"), closeSoftKeyboard())
        onView(withId(R.id.inputEmail)).perform(typeText("test@email.com"), closeSoftKeyboard())
        onView(withId(R.id.inputPassword)).perform(typeText("password123"), closeSoftKeyboard())

        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.displayConfirmPassword)).check(matches(hasError()))

    }

    @Test
    fun checkTitleTextDisplayed() {
        onView(withId(R.id.signUpTXT))
            .check(matches(isDisplayed()))
            .check(matches(withText("Find Your Perfect Home")))
    }

    @Test
    fun checkGoToLogin() {
        Intents.intending(IntentMatchers.hasComponent(Login::class.java.name)).respondWith(
            Instrumentation.ActivityResult(Activity.RESULT_OK, null)
        )
        onView(withId(R.id.loginTxt)).perform(click())
        Intents.intended(IntentMatchers.hasComponent(Login::class.java.name))
    }

    @Test
    fun checkImageViewDisplayed() {
        onView(withId(R.id.logo))
            .check(matches(isDisplayed()))
    }


    private fun hasError(): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("has error")
            }

            override fun matchesSafely(item: View): Boolean {
                if (item !is TextInputLayout) return false
                return item.error != null
            }
        }
    }

    @After
    fun cleanUp() {
        Intents.release()
    }
}