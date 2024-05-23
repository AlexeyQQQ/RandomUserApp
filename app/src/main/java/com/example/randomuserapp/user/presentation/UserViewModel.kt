package com.example.randomuserapp.user.presentation

import androidx.lifecycle.ViewModel
import com.example.randomuserapp.user.data.UserRepository

class UserViewModel(
    private var repository: UserRepository,
) : ViewModel() {

    fun init(firstRun: Boolean): UserUiState {
        return if (firstRun) {
            repository.saveLastScreen()
            val userInfo = repository.userInfo()
            UserUiState.ShowInfo(
                imageUrl = userInfo.imageUrl,
                firstName = userInfo.firstName,
                lastName = userInfo.lastName,
                gender = userInfo.gender,
                phone = userInfo.phone,
            )
        } else {
            UserUiState.Empty
        }
    }
}
