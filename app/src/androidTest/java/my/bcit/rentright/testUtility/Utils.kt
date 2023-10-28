package my.bcit.rentright.testUtility

import android.view.View
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class Utils {

    fun hasError(): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("has error")
            }

            override fun matchesSafely(item: View?): Boolean {
                if (item !is TextInputLayout) return false
                return item.error != null
            }
        }
    }

}