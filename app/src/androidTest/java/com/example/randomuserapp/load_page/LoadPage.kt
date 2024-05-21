package com.example.randomuserapp.load_page

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.example.randomuserapp.R
import com.example.randomuserapp.custom_matchers.Wait
import org.hamcrest.Matcher

class LoadPage {

    private val rootId: Matcher<View> = withParent(withId(R.id.loadLayout))
    private val rootClass: Matcher<View> = withParent(isAssignableFrom(LinearLayout::class.java))

    private val progressBar = ProgressUi(rootId = rootId, rootClass = rootClass)
    private val errorText = ErrorUi(rootId = rootId, rootClass = rootClass)
    private val retryButton = RetryUi(rootId = rootId, rootClass = rootClass)

    fun checkProgressState() {
        progressBar.checkVisible()
        errorText.checkNotVisible()
        retryButton.checkNotVisible()
    }

    fun checkErrorState(message: String) {
        progressBar.checkNotVisible()
        errorText.checkVisible(message = message)
        retryButton.checkVisible()
    }

    fun clickRetry() {
        retryButton.click()
    }

    fun waitError() {
        errorText.waitUntilVisible()
    }

    fun waitDisappear() {
        onView(isRoot()).perform(Wait(1100))
    }
}
