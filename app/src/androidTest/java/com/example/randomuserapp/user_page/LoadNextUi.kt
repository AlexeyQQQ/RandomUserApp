package com.example.randomuserapp.user_page

import android.view.View
import android.widget.Button
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.randomuserapp.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class LoadNextUi(rootId: Matcher<View>, rootClass: Matcher<View>) {

    private val interaction = onView(
        allOf(
            withId(R.id.loadNextButton),
            withText(R.string.load_next_user),
            isAssignableFrom(Button::class.java),
            rootId,
            rootClass,
        )
    )

    fun click() {
        interaction.perform(androidx.test.espresso.action.ViewActions.click())
    }
}