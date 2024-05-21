package com.example.randomuserapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.randomuserapp.load_page.LoadPage
import com.example.randomuserapp.user_page.UserPage
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * TestCase N1
     * 1. Запуск приложения
     * State progress
     * 2. Ожидание ошибки
     * State error
     * 3. Клик по Retry button
     * State progress
     * 4. Ожидание загрузки
     * State show user info
     * 5. Клик по Load next user button
     * State progress
     */
    @Test
    fun testCase1() {
        // step 1
        val loadPage = LoadPage()
        loadPage.checkProgressState()

        scenarioRule.scenario.recreate()
        loadPage.checkProgressState()

        // step 2
        loadPage.waitError()
        loadPage.checkErrorState(message = "No internet connection")

        scenarioRule.scenario.recreate()
        loadPage.checkErrorState(message = "No internet connection")

        // step 3
        loadPage.clickRetry()
        loadPage.checkProgressState()

        scenarioRule.scenario.recreate()
        loadPage.checkProgressState()

        // step 4
        loadPage.waitDisappear()
        val userPage = UserPage(
            pictureUrl = "https://randomuser.me/api/portraits/men/45.jpg",
            firstName = "Eelis",
            lastName = "Neva",
            gender = "male",
            phone = "02-255-856",
        )
        userPage.checkShowUserInfoState()

        scenarioRule.scenario.recreate()
        userPage.checkShowUserInfoState()

        // step 5
        userPage.clickLoadNextUser()
        loadPage.checkProgressState()
    }
}