package com.example.randomuserapp

import com.example.randomuserapp.user.data.UserInfo
import com.example.randomuserapp.user.data.UserRepository
import com.example.randomuserapp.user.presentation.UserUiState
import com.example.randomuserapp.user.presentation.UserViewModel
import org.junit.Assert.assertEquals
import org.junit.Test


class UserViewModelTest {

    @Test
    fun testCaseNumber1() {
        val repository = FakeUserRepository()
        val viewModel = UserViewModel(repository = repository)

        // step 1 state show info
        var actualUiState: UserUiState = viewModel.init(firstRun = true)
        var expectedUiState: UserUiState = UserUiState.ShowInfo(
            imageUrl = "",
            firstName = "Eelis",
            lastName = "Neva",
            gender = "male",
            phone = "02-255-856"
        )
        assertEquals(expectedUiState, actualUiState)
        assertEquals(true, repository.lastScreenIsSaved)

        // step 2 rotate screen
        actualUiState = viewModel.init(firstRun = false)
        expectedUiState = UserUiState.Empty
        assertEquals(expectedUiState, actualUiState)
    }
}

private class FakeUserRepository : UserRepository {

    private val userInfoList = listOf<UserInfo>(
        UserInfo(
            imageUrl = "",
            firstName = "Eelis",
            lastName = "Neva",
            gender = "male",
            phone = "02-255-856",
        ),
        UserInfo(
            imageUrl = "",
            firstName = "Abbie",
            lastName = "Chavez",
            gender = "female",
            phone = "015242 77010",
        )
    )
    private var currentIndex = 0

    var lastScreenIsSaved: Boolean = false

    override fun userInfo(): UserInfo {
        if (currentIndex == 2) currentIndex = 0
        return userInfoList[currentIndex++]
    }

    override fun saveLastScreen() {
        lastScreenIsSaved = true
    }
}