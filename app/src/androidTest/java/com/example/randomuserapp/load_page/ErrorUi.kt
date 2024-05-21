package com.example.randomuserapp.load_page

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.randomuserapp.R
import com.example.randomuserapp.custom_matchers.WaitAction
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class ErrorUi(rootId: Matcher<View>, rootClass: Matcher<View>) {

    private val id: Int = R.id.errorTextView

    private val interaction = onView(
        allOf(
            withId(id),
            isAssignableFrom(TextView::class.java),
            rootId,
            rootClass,
        )
    )

    fun checkVisible(message: String) {
        interaction.check(matches(isDisplayed()))
            .check(matches(withText(message)))
    }

    fun checkNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun waitUntilVisible() {
        onView(isRoot()).perform(WaitAction(id, isDisplayed(), 1100))
    }
}

