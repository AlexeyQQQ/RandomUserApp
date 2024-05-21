package com.example.randomuserapp.user_page

import android.view.View
import android.widget.ImageView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.randomuserapp.R
import com.example.randomuserapp.custom_matchers.DrawableMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class PictureUi(rootId: Matcher<View>, rootClass: Matcher<View>) {

    private val interaction = onView(
        allOf(
            withId(R.id.pictureImageView),
            isAssignableFrom(ImageView::class.java),
            rootId,
            rootClass,
        )
    )

    fun checkShowUserInfoState() {
        interaction.check(matches(DrawableMatcher(R.mipmap.ic_launcher)))
            .check(matches(isDisplayed()))
    }
}
