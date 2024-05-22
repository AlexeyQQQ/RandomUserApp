package com.example.randomuserapp

import com.example.randomuserapp.main.presentation.MainRepository
import com.example.randomuserapp.main.presentation.MainViewModel
import com.example.randomuserapp.main.presentation.Screen
import org.junit.Assert.assertEquals
import org.junit.Test

class MainViewModelTest {

    @Test
    fun testCaseNumber1() {
        val repository = FakeMainRepository()
        val viewModel = MainViewModel(repository = repository)

        // step 1 load saved screen
        var actualScreen: Screen = viewModel.init(firstRun = true)
        assertEquals(FakeScreen, actualScreen)

        // step 2 rotate screen
        actualScreen = viewModel.init(firstRun = false)
        assertEquals(Screen.Empty, actualScreen)
    }
}

private class FakeMainRepository : MainRepository {

    override fun lastSavedScreen(): Screen = FakeScreen
}

private object FakeScreen : Screen