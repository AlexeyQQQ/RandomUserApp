package com.example.randomuserapp.user_page

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.example.randomuserapp.R
import org.hamcrest.Matcher

class UserPage(
    private val firstName: String,
    private val lastName: String,
    private val gender: String,
    private val phone: String
) {

    private val rootId: Matcher<View> = withParent(withId(R.id.userLayout))
    private val rootClass: Matcher<View> = withParent(isAssignableFrom(LinearLayout::class.java))

    private val pictureUrlView = PictureUi(rootId = rootId, rootClass = rootClass)
    private val firstNameView =
        TextUi(id = R.id.firstNameTextView, rootId = rootId, rootClass = rootClass)
    private val lastNameView =
        TextUi(id = R.id.lastNameTextView, rootId = rootId, rootClass = rootClass)
    private val genderView =
        TextUi(id = R.id.genderTextView, rootId = rootId, rootClass = rootClass)
    private val phoneView =
        TextUi(id = R.id.phoneTextView, rootId = rootId, rootClass = rootClass)
    private val loadNextButton = LoadNextUi(rootId = rootId, rootClass = rootClass)

    fun checkShowUserInfoState() {
        pictureUrlView.checkShowUserInfoState()
        firstNameView.checkShowUserInfoState(text = firstName)
        lastNameView.checkShowUserInfoState(text = lastName)
        genderView.checkShowUserInfoState(text = gender)
        phoneView.checkShowUserInfoState(text = phone)
        loadNextButton.checkShowUserInfoState()
    }

    fun clickLoadNextUser() {
        loadNextButton.click()
    }
}
